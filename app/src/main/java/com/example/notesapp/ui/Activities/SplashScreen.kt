package com.example.notesapp.ui.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.notesapp.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar!!.hide()
        Handler(Looper.getMainLooper()).postDelayed({
         var intent=  Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
          //  startActivity(Intent(this, MainActivity::class.java))
        }, 2000)
    }
}