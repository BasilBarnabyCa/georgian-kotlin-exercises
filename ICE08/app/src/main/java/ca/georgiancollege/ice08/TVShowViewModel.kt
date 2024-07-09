package ca.georgiancollege.ice08

import androidx.lifecycle.*
import kotlinx.coroutines.*

class TVShowViewModel : ViewModel() {
    private val dataManager = DataManager.instance()

    private val m_tvShows = MutableLiveData<List<TVShow>>()
    val tvShows: LiveData<List<TVShow>> get() = m_tvShows

    private val m_tvShow = MutableLiveData<TVShow?>()
    val tvShow: LiveData<TVShow?> get() = m_tvShow

    // Load all TV Shows from the database manager
    fun loadAllTVShows() {
        viewModelScope.launch {
            m_tvShows.value = dataManager.getAllTVShows()
        }
    }

    // Load a specific TV Show from the database manager
    fun loadTVShowById(id: String) {
        viewModelScope.launch {
            m_tvShow.value = dataManager.getTVShowById(id)
        }
    }

    // Save or update a TV Show in the database manager
    fun saveTVShow(tvShow: TVShow) {
        viewModelScope.launch {
            if (tvShow.id.isEmpty()) {
                dataManager.insert(tvShow)
            } else {
                dataManager.update(tvShow)
            }
            loadAllTVShows()
        }
    }

    // Delete a TV Show from the database manager
    fun deleteTVShow(tvShow: TVShow) {
        viewModelScope.launch {
            dataManager.delete(tvShow)
            loadAllTVShows()
        }
    }
}