package ca.georgiancollege.ice09

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ca.georgiancollege.ice09.databinding.ActivityDetailsBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.UUID

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val viewModel: TVShowViewModel by viewModels()

    private lateinit var dataManager: DataManager

    private var tvShowId: String? = null

    private lateinit var auth: FirebaseAuth

    private val inactivityHandler = Handler(Looper.getMainLooper())
    private val inactivityRunnable = Runnable {
        auth.signOut()
    }

    private var timer: Int = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Alias for the DataManager singleton
        dataManager = DataManager.instance()

        tvShowId = intent.getStringExtra("tvShowId")

        if (tvShowId != null) {
            viewModel.loadTVShowById(tvShowId!!)
        } else {
            binding.deleteButton.visibility = View.GONE
        }

        // Observe the LiveData from the ViewModel
        viewModel.tvShow.observe(this) { tvShow ->
            tvShow?.let {
                binding.titleEditText.setText(tvShow.title)
                binding.genreEditText.setText(tvShow.genre)
                binding.ratingEditText.setText(tvShow.rating.toString())
            }
        }

        binding.saveButton.setOnClickListener {
            saveTVShow()
        }

        binding.deleteButton.setOnClickListener {
            deleteTVShow()
        }

        binding.cancelButton.setOnClickListener {
            if (auth.currentUser != null) {
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        resetInactivityTimer()
    }

    private fun saveTVShow() {

        if (auth.currentUser != null) {
            // Getting Values from layout
            val title = binding.titleEditText.text.toString()
            val genre = binding.genreEditText.text.toString()
            val rating = binding.ratingEditText.text.toString().toDoubleOrNull() ?: 0.0

            if (title.isNotEmpty() && genre.isNotEmpty()) {
                val tvShow = TVShow(
                    id = tvShowId ?: UUID.randomUUID().toString(),
                    title = title,
                    genre = genre,
                    rating = rating
                )

                viewModel.saveTVShow(tvShow)
                Toast.makeText(this, "TV Show Saved!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Title and genre is required", Toast.LENGTH_SHORT).show()
            }
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun deleteTVShow() {
        if (auth.currentUser != null) {
            tvShowId?.let { _ ->
                AlertDialog.Builder(this)
                    .setTitle("Delete TV Show")
                    .setMessage("Are you sure you want to delete this TV show?")
                    .setPositiveButton("Yes") { _, _ ->
                        viewModel.tvShow.value?.let {
                            viewModel.deleteTVShow(it)
                            Toast.makeText(this, "TV Show Deleted!", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                    .setNegativeButton("No", null)
                    .show()
            }
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        resetInactivityTimer()
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        resetInactivityTimer()
    }

    private fun resetInactivityTimer() {
        inactivityHandler.removeCallbacks(inactivityRunnable)
        inactivityHandler.postDelayed(inactivityRunnable, timer * 1000L)
    }

    override fun onPause() {
        super.onPause()
        inactivityHandler.removeCallbacks(inactivityRunnable)
    }
}