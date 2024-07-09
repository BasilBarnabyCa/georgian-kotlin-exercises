package ca.georgiancollege.ice08

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// This annotation marks hte class as a room database
@Database(entities = [TVShow::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase()
{
    abstract  fun tvShowDao(): TVShowDao

    companion object
    {

        // Volatile annotation marks the variable as visible to all threads
        @Volatile
        private  var m_instance: AppDatabase? = null

        // This function returns the singleton instance of the AppDatabase class
        // If the instance is null, it creates a new instance of the AppDatabase class using Room.databaseBuilder
        fun getDatabase(context: Context): AppDatabase
        {
            // Multiple threads can access the database at the same time
            // ensuring that only one thread can access the database at a time
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
}