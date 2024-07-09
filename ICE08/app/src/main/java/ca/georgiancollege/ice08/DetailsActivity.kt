package ca.georgiancollege.ice08

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ca.georgiancollege.ice08.databinding.ActivityDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private lateinit var dataManager: DataManager

    private var tvShowId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Alias for the DataManager singleton
        dataManager = DataManager.instance(this)

        tvShowId = intent.getIntExtra("tvShowId", -1).takeIf { it != -1 }

        if (tvShowId != null) {
            loadTVShow(tvShowId!!)
        } else {
            binding.deleteButton.visibility = View.GONE
        }

        binding.saveButton.setOnClickListener {
            saveTVShow()
        }

        binding.deleteButton.setOnClickListener {
            deleteTVShow()
        }

        binding.cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun loadTVShow(id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val tvShow = dataManager.getTVShowById(id)
            tvShow?.let {
                binding.titleEditText.setText(tvShow.title)
                binding.genreEditText.setText(tvShow.genre)
                binding.ratingEditText.setText(tvShow.rating.toString())
            }
        }
    }

    private fun saveTVShow() {

        // Getting Values from layout
        val title = binding.titleEditText.text.toString()
        val genre = binding.genreEditText.text.toString()
        val rating = binding.ratingEditText.text.toString().toDoubleOrNull() ?: 0.0

        if (title.isNotEmpty() && genre.isNotEmpty()) {
            val tvShow = TVShow(
                id = tvShowId ?: 0,
                title = title,
                genre = genre,
                rating = rating
            )

            CoroutineScope(Dispatchers.Main).launch {
                if (tvShowId == null) {
                    dataManager.insert(tvShow)
                    Toast.makeText(this@DetailsActivity, "TV Show Added", Toast.LENGTH_SHORT).show()
                } else {
                    dataManager.update(tvShow)
                    Toast.makeText(this@DetailsActivity, "TV Show Updated", Toast.LENGTH_SHORT)
                        .show()
                }
                finish()
            }
        } else {
            Toast.makeText(this, "Title and genre is required", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteTVShow() {
        tvShowId?.let { id ->
            AlertDialog.Builder(this)
                .setTitle("Delete TV Show")
                .setMessage("Are you sure you want to delete this TV show?")
                .setPositiveButton("Yes") { _, _ ->
                    CoroutineScope(Dispatchers.Main).launch {
                        val tvShow = dataManager.getTVShowById(id)
                        tvShow?.let {
                            dataManager.delete(tvShow)
                            Toast.makeText(
                                this@DetailsActivity,
                                "TV Show Deleted!",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        }
                    }
                }
                .setNegativeButton("No", null)
                .show()
        }
    }
}