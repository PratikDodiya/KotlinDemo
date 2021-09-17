package com.app.kotlindemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.app.kotlindemo.R

class SplashActivity : AppCompatActivity() {

    var mDelayHandler: Handler? = null;
    val SPLASH_DELAY: Long = 3000 //3 seconds

    val runnable: Runnable = Runnable {
        if(!isFinishing) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(runnable, SPLASH_DELAY)
    }

    override fun onDestroy() {
        super.onDestroy()

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(runnable)
        }
    }
}
