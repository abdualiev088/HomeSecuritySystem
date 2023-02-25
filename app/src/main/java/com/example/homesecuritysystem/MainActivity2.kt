package com.example.homesecuritysystem

import android.net.Uri.parse
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.Formatter
import android.util.Log.d
import android.util.Log.e
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homesecuritysystem.Api.ApiInterface
import com.example.homesecuritysystem.Api.MyDataItem
import com.example.homesecuritysystem.Counter.Room.CountViewModel
import com.example.homesecuritysystem.Counter.Room.Count_Room
import com.example.homesecuritysystem.Counter.Server.Count
import com.example.homesecuritysystem.databinding.ActivityMain2Binding
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.net.InetAddress
import java.net.URL
import kotlin.concurrent.thread



class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
//    192.168.1.101 - Home wifi
    val BASE_URL = "http://172.18.2.85:5000/"

    //    ADAPTER
    lateinit var personAdapter: PersonAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

//    ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

//        val wm = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
//        val wInfo = wm.connectionInfo
//        val ipAddress = Formatter.formatIpAddress(wInfo.ipAddress)
//        d("MyLog", "$ipAddress")

        lateinit var countViewModel: CountViewModel

//        countViewModel = ViewModelProvider(this).get(CountViewModel::class.java)
//
//        countViewModel.addCount(Count_Room(1))

//        d("MyLog", "${countViewModel.readAllData.value?.count}")
//
//        countViewModel.readAllData.observe(this, Observer {
//            if (it == null) {
//                val count = Count_Room(0)
//                countViewModel.addCount(count)
//            }
////            d("MyLog", "Room data: $it")
//        })
//
//        countViewModel.addCount(Count_Room(0))
        getMyData()
        getCountFromServer()
        onClickKey()
//        d("MyLog", "${countViewModel.readAllData.value?.count!!}")

        binding.rcViewPerson.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this@MainActivity2)
        binding.rcViewPerson.layoutManager = linearLayoutManager

        this.supportActionBar?.setElevation(0F)
    }

    private fun getMyData() {
        val retrofitBuilder =  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object: Callback<List<MyDataItem>?>{
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responseBody = response.body()!!

                personAdapter = PersonAdapter(baseContext, responseBody)
                personAdapter.notifyDataSetChanged()
                binding.rcViewPerson.adapter = personAdapter

                personAdapter.setOnClickListener(object : PersonAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {
                        thread {
                            var url = "https://sgp1.blynk.cloud/external/api/update?token=BF6rPUf64b7bDSRjDNY-zD011KWl5B79&v7=0"
                            val json = try {
                                URL(url).readText()
                            } catch (e: Exception) {
                                return@thread
                            }
                            runOnUiThread { (json) }
                        }
                        Toast.makeText(this@MainActivity2, "You clicked item no. $position", Toast.LENGTH_SHORT).show()

                    }

                })
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                d("MyLog", "onFailure: " + t.message)
            }

        })


    }

    private fun getCountFromServer() {
        val retrofitBuilder =  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getCount()

        retrofitData.enqueue(object: Callback<Count>{
            override fun onResponse(
                call: Call<Count>,
                response: Response<Count>
            ) {
                val responseBody = response.body()!!

                binding.FCount.text = responseBody.familiar.toString()
                binding.UnFCount.text = responseBody.unfamiliar.toString()
//                Toast.makeText(applicationContext, "${responseBody.all}, ${responseBody.familiar}, ${responseBody.unfamiliar}", Toast.LENGTH_SHORT).show()
//                notifyMe(responseBody.count)
            }

            override fun onFailure(call: Call<Count>, t: Throwable) {
                d("MyLog", "onFailure: " + t.message)
            }

        })

    }

    private fun onClickKey(){
        binding.keyButton.setOnClickListener {
            thread {
                var url = "https://sgp1.blynk.cloud/external/api/update?token=BF6rPUf64b7bDSRjDNY-zD011KWl5B79&v7=1"
                val json = try {
                    URL(url).readText()
                } catch (e: Exception) {
                    return@thread
                }
                runOnUiThread { (json) }
            }
            Toast.makeText(applicationContext, "Door opened", Toast.LENGTH_SHORT).show()
        }
    }

//    fun putMethod() {
//
////        // Create Retrofit
////        val retrofit = Retrofit.Builder()
////            .baseUrl(BASE_URL)
////            .build()
////
////        // Create Service
////        val service = retrofit.create(ApiInterface::class.java)
////
////        // Create JSON using JSONObject
////        val jsonObject = JSONObject()
////        jsonObject.put("name", "Nicole")
////        jsonObject.put("job", "iOS Developer")
////
////        // Convert JSONObject to String
////        val jsonObjectString = jsonObject.toString()
////
////        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
////        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
////
////        CoroutineScope(Dispatchers.IO).launch {
////
////            // Do the PUT request and get response
////            val response = service.update(requestBody)
////
////            withContext(Dispatchers.Main) {
////                if (response.isExecuted) {
////
////                    // Convert raw JSON to pretty JSON using GSON library
////                    val gson = GsonBuilder().setPrettyPrinting().create()
////                    val prettyJson = gson.toJson(
////                        JsonParser.parse(
////                            response.body()
////                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
////                        )
////                    )
////
////                    d("Pretty Printed JSON :", prettyJson)
////
////                } else {
////
////                    e("RETROFIT_ERROR", response.code().toString())
////
////                }
////            }
////        }
//    }
}