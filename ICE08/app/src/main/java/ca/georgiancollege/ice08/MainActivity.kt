package ca.georgiancollege.ice08

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.ice08.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var dataManager: DataManager

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

        dataManager = DataManager.instance(this)

        binding.firstRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.firstRecyclerView.adapter = adapter

        loadTVShows()

        binding.addButton.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }



//        // create an array of TV shows (mock data)
//        val favouriteTVShows = arrayOf(
//            TVShow("House of the Dragon", "HBO"),
//            TVShow("Lord of the Rings", "Prime Video"),
//            TVShow("Andor", "Disney"),
//            TVShow("Severance", "AppleTv"),
//            TVShow("Star Trek: Strange New Worlds", "Paramount+")
//        )
//
//        // create an instance of the FirstAdapter and pass in the array of TV shows
//        val firstAdapter = FirstAdapter(favouriteTVShows)
//        binding.firstRecyclerView.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = firstAdapter
//        }
    }

    override fun onResume() {
        super.onResume()
        loadTVShows()
    }

    private fun loadTVShows() {
       lifecycleScope.launch {
           val tvShows = dataManager.getAllTvVShows()
           adapter.submitList(tvShows)
       }
    }
}