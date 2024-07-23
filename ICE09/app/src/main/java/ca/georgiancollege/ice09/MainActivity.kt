package ca.georgiancollege.ice09

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.ice09.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: TVShowViewModel by viewModels()

    private lateinit var dataManager: DataManager

    private lateinit var auth: FirebaseAuth

    // Timer for inactivity in seconds
    private val timer: Int = 10

    // Handler and Runnable for the inactivity timer
    private val inactivityHandler = Handler(Looper.getMainLooper())
    private val inactivityRunnable = Runnable {
        auth.signOut()
        Toast.makeText(this, "Session timed out", Toast.LENGTH_SHORT).show()
    }

    // Adapter for the RecyclerView, with a click listener to open the DetailsActivity
    private val adapter = TVShowListAdapter {tvShow: TVShow ->
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra("tvShowId", tvShow.id)
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Initialize our Firestore and DataManager
        FirebaseFirestore.setLoggingEnabled(true)
        dataManager = DataManager.instance()

        binding.firstRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.firstRecyclerView.adapter = adapter

        // Observe the LiveData from the ViewModel
        viewModel.tvShows.observe(this) { tvShows ->
            adapter.submitList(tvShows)
        }

        // Load all TV Shows from the database manager via viewModel
        viewModel.loadAllTVShows()

        // Floating Action Button (FAB) to add a new TV Show
        binding.addMovieFAB.setOnClickListener {
            if (auth.currentUser != null) {
                val intent = Intent(this, DetailsActivity::class.java)
                startActivity(intent)
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        binding.logoutButton.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Start the inactivity timer
        resetInactivityTimer()
    }

    override fun onResume() {
        super.onResume()
        resetInactivityTimer()
        viewModel.loadAllTVShows()
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        resetInactivityTimer()
    }

    private fun resetInactivityTimer() {
        inactivityHandler.removeCallbacks(inactivityRunnable)
        inactivityHandler.postDelayed(inactivityRunnable, timer * 1000L)
    }

    override fun onDestroy() {
        super.onDestroy()
        inactivityHandler.removeCallbacks(inactivityRunnable)
    }
}