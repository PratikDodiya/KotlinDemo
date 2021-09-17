package com.app.kotlindemo

import android.app.Application
import android.content.Context

/**
 * Created by Pratik on 7/5/19.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        var application: Context? = null
    }
}