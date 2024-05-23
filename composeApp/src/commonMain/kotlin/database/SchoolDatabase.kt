package database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [School::class],
    version = 1
)
abstract class SchoolDatabase : RoomDatabase() {

    abstract fun schoolDao(): SchoolDao
}