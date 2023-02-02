package com.example.homesecuritysystem.Counter.Room

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountViewModel(application: Application):ViewModel() {
    val readAllData: LiveData<Count_Room>
    private val repository: CountRepository

    init {
        val countDao = CountDatabase.getDatabase(application).studentDao()
        repository = CountRepository(countDao)
        readAllData = repository.readAllData
    }

    fun addCount(count: Count_Room){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCount(count)
        }
    }
    fun updateCount(count: Count_Room){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCount(count)
        }
    }
}