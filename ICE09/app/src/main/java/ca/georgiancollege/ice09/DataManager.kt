package ca.georgiancollege.ice09

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class DataManager private constructor()
{
    private val db: FirebaseFirestore = Firebase.firestore

    companion object
    {
        private val TAG = "DataManager"

        @Volatile
        private var m_instance: DataManager? = null

        fun instance(): DataManager
        {
            if(m_instance == null)
            {
                synchronized(this)
                {
                    if (m_instance == null) {
                        m_instance = DataManager()
                    }
                }
            }

            return m_instance!!
        }
    }

    // Add TV Show to Firestore
    suspend fun insert(tvShow: TVShow)  {
        try {
            db.collection("tvShows").document(tvShow.id).set(tvShow).await()
        }
        catch (e: Exception) {
            Log.e(TAG, "Error inserting TV Show: ${e.message}", e)
        }
    }

    // Update TV Show in Firestore
    suspend fun update(tvShow: TVShow) {
        try {
            db.collection("tvShows").document(tvShow.id).set(tvShow).await()
        } catch (e: Exception) {
            Log.e(TAG, "Error updating TV Show: ${e.message}", e)
        }
    }

    // Delete TV Show from Firestore
    suspend fun delete(tvShow: TVShow) {
        try {
            db.collection("tvShows").document(tvShow.id).delete().await()
        } catch (e: Exception) {
            Log.e(TAG, "Error deleting TV Show: ${e.message}", e)
        }
    }

    // Get all TV Shows from Firestore
    suspend fun getAllTVShows() : List<TVShow> {
       return try {
           val tvShows = db.collection("tvShows").get().await()
           tvShows?.toObjects(TVShow::class.java) ?: emptyList()
       } catch (e: Exception) {
           Log.e(TAG, "Error getting all TV Shows: ${e.message}", e)
           emptyList()
       }
    }

    // Get TV Show by ID from Firestore
    suspend fun getTVShowById(id: String) : TVShow? {
        return try {
            val tvShow = db.collection("tvShows").document(id).get().await()
            tvShow?.toObject(TVShow::class.java)
        } catch (e: Exception) {
            Log.e(TAG, "Error getting TV Show with id ${id}: ${e.message}", e)
            null
        }
    }

    // User CRUD operations
    // Add User to Firestore
    suspend fun insertUser(user: User)  {
        try {
            db.collection("users").document(user.id).set(user).await()
        }
        catch (e: Exception) {
            Log.e(TAG, "Error inserting User: ${e.message}", e)
        }
    }

    // Get User by ID from Firestore
    suspend fun getUserById(id: String) : User? {
        return try {
            val user = db.collection("users").document(id).get().await()
            user?.toObject(User::class.java)
        } catch (e: Exception) {
            Log.e(TAG, "Error getting User with id ${id}: ${e.message}", e)
            null
        }
    }

}