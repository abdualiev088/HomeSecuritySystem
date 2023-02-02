package com.example.homesecuritysystem.Counter.Room

import androidx.lifecycle.LiveData

class CountRepository(private val countDao: CountDao) {
    val readAllData: LiveData<Count_Room> = countDao.readData()

    suspend fun addCount(count: Count_Room){
        countDao.addCounter(count)
    }
    fun updateCount(count: Count_Room){
        countDao.update(count)
    }
}