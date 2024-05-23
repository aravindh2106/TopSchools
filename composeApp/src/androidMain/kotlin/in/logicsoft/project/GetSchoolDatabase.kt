package `in`.logicsoft.project

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import database.SchoolDatabase

fun getSchoolDatabase(context: Context): SchoolDatabase {
    val dbFile = context.getDatabasePath("school.db")
    return Room.databaseBuilder<SchoolDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}

