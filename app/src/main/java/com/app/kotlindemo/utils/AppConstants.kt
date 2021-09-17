package com.app.kotlindemo.utils

/**
 * Created by Pratik on 8/5/19.
 */
object AppConstants {

    internal val APP_DB_NAME = "app_db.db"
    internal val PREF_NAME = "app_pref"


    const val BLUR_RADIUS = 35

    enum class LoggedInMode constructor(val type: Int) {
        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3)
    }
}