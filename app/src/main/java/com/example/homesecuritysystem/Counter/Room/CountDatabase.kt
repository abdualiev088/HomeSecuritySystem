package com.example.homesecuritysystem.Counter.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Room::class],  version = 1, exportSchema = false )
abstract class CountDatabase: RoomDatabase() {
    abstract fun studentDao(): CountDao

    //    Everything  within companion object will be basically visible to other classes
    companion object{
        //        UserDatabase singletone class -> Our StudentDatabase will have only one instance of its class
        @Volatile
        private var INSTANCE: CountDatabase? = null

        fun getDatabase(context: Context): CountDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CountDatabase::class.java,
                    "count_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}