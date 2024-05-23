import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import database.SchoolDatabase


fun getSchoolDatabase(): SchoolDatabase {
    val dbFile = NSHomeDirectory() + "/school.db"
    return Room.databaseBuilder<SchoolDatabase>(
        name = dbFile,
        factory = {
            SchoolDatabase::class.instantiateImpl()
        }
    ).setDriver(BundledSQLiteDriver()).build()
}