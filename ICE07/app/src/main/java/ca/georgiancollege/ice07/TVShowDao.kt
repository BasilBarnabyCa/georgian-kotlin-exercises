package ca.georgiancollege.ice07

import androidx.room.*

@Dao
interface TVShowDao
{
    // Inserts a TV show into the db
    // If tv show has same ID already exists it will replace
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShow: TVShow)

    // Update existing record
    @Update()
    suspend fun update(tvShow: TVShow)

    // Delete record
    @Delete()
    suspend fun delete(tvShow: TVShow)

    // Gets All records
    @Query("SELECT * from tv_shows")
    suspend fun getAllTvVShows(): List<TVShow>

    // Get record by ID
    @Query("SELECT * FROM tv_shows WHERE id = :id")
    suspend fun getTVShowById(id: Int): TVShow?
}