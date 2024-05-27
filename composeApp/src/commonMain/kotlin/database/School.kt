package database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class School(
    val schoolName: String,
    val description: String,
    val city: String,
    @PrimaryKey val id: Int
)
