import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import database.SchoolDatabase
import java.io.File

fun getSchoolDatabase(): SchoolDatabase {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "school.db")
    return Room.databaseBuilder<SchoolDatabase>(
        name = dbFile.absolutePath,
    ).setDriver(BundledSQLiteDriver()).build()
}

