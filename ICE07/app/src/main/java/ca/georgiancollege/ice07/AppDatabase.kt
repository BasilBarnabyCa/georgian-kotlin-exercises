package ca.georgiancollege.ice07

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TVShow::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase()
{
    abstract  fun tvShowDao(): TVShowDao

    companion object
    {
        // Volatile annotation marks the variable as visible to all threads
        @Volatile
        private  var m_instance: AppDatabase? = null
    }

    // Multiple threads can access the database at the same time
    // ensuuring that only one thread can access the database at a time
    fun getDatabase(context: Context): AppDatabase
    {
        return m_instance ?: synchronized(this) {
            // Create an instance of the database using Room.DatabaseBuilder
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "media_database"
            ).build()

            // Assign the newly created database instance to the m_instance variable
            m_instance = instance

            // Return the instance
            instance
        }
    }
}