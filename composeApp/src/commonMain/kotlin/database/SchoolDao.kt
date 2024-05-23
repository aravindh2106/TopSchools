package database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SchoolDao {

    @Upsert
   suspend fun upsert(school: School)

    @Delete
   suspend fun delete(school: School)

    @Query("select * from school")
     fun getAllSchool(): Flow<List<School>>
}