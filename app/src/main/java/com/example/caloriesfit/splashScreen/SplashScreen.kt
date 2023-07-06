package com.example.caloriesfit.splashScreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.caloriesfit.R
import com.example.caloriesfit.mainTabs.TabsLayout

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({startTabActivity()},1000)

    }

    private fun startTabActivity() {
        val intentToTabs = Intent(this@SplashScreen, TabsLayout::class.java)
        startActivity(intentToTabs)
        finish()
    }

}