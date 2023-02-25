package com.example.homesecuritysystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

import android.view.WindowManager
import android.widget.Button
import com.example.homesecuritysystem.databinding.SplashScreenLayoutBinding

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    lateinit var binding: SplashScreenLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = SplashScreenLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this, MainActivity2::class.java).apply {
                startActivity(this)
                finish()
            }
        }, 3000)

    }

}