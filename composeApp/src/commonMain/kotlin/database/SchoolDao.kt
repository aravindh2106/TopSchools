package database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SchoolDao {

    @Insert
   suspend fun insert(school: School)

    @Update
    suspend fun update(school: School)

    @Delete
   suspend fun delete(school: School)

    @Query("select * from school")
     fun getAllSchool(): Flow<List<School>>

     @Query("select * from school where id= :schoolId")
    suspend fun getById(schoolId:Int): School
}