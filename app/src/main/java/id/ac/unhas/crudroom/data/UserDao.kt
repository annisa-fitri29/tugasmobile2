package id.ac.unhas.crudroom.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user:User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData():LiveData<List<User>>
}