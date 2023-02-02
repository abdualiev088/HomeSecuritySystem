package com.example.homesecuritysystem.Counter.Room

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "count_table")
data class Count_Room(
    @ColumnInfo()
    val count: Int
)