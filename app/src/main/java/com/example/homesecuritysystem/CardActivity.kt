package com.example.homesecuritysystem

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputBinding
import android.widget.Switch
import com.example.homesecuritysystem.databinding.ActivityCardBinding

class CardActivity : AppCompatActivity() {
    lateinit var binding: ActivityCardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCardBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }


}
