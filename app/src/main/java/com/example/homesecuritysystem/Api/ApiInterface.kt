package com.example.homesecuritysystem.Api

import androidx.room.Update
import com.example.homesecuritysystem.Counter.Room.Count_Room
import com.example.homesecuritysystem.Counter.Server.Count
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT

interface ApiInterface {
    @GET("all_people")
    fun getData(): Call<List<MyDataItem>>

    @GET("count")
    fun getCount(): Call<Count>


    @PUT("update_person_status")
    fun update(id:RequestBody): Call<UpdateStatus>
}