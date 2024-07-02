package ca.georgiancollege.ice07

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity annotation marks this class as a table in the room database
@Entity(tableName = "tv_shows")

// Model Class
data class TVShow(
    // Marks this field as a primary key and autogenerate makes the field autogenerate
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Title column
    val title: String,

    // Genre column
    val genre: String,

    // Rating column
    val rating: Double
)