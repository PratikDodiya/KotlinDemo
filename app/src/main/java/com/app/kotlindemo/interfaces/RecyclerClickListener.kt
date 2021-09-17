package com.app.kotlindemo.interfaces

import android.view.View

/**
 * Created by Pratik on 7/5/19.
 */
interface RecyclerItemClickListener {
    fun onItemClickEvent(view: View, position: Int, type: Int)
}