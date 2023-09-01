package com.example.baseprojectsetup.ui.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.baseprojectsetup.R
import com.mkrlabs.common.core.base.utils.AppConstant.SPLASH_DISPLAY_LENGTH
import com.example.baseprojectsetup.ui.MainActivity
import com.mkrlabs.common.core.base.GifLoader.startActivity
import com.mkrlabs.common.core.base.utils.DataStoreManager
import com.mkrlabs.dashboard.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        delayAndMoveToMainActivity()
    }

    fun delayAndMoveToMainActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, SPLASH_DISPLAY_LENGTH)
    }





}