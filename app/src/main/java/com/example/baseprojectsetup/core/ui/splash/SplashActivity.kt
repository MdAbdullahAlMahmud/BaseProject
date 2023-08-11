package com.example.baseprojectsetup.core.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.baseprojectsetup.R
import com.example.baseprojectsetup.core.base.utils.AppConstant.SPLASH_DISPLAY_LENGTH
import com.example.baseprojectsetup.core.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        delayAndMoveToAnother()
    }

    fun delayAndMoveToAnother(){
        Handler(Looper.getMainLooper()).postDelayed({ /* Create an Intent that will start the MainActivity. */
            val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, SPLASH_DISPLAY_LENGTH)
    }
}