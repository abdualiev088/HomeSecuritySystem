package com.example.homesecuritysystem.Counter.Room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CountDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCounter(count: Count_Room)

    @Query("SELECT count FROM count_table")
    fun readData(): LiveData<Count_Room>

    @Update(entity = Count_Room::class)
    fun update(vararg count: Count_Room)

}