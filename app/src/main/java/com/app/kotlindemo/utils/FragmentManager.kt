package com.app.kotlindemo.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.app.kotlindemo.R

/**
 * Created by Pratik on 8/5/19.
 */
/*
internal fun FragmentManager.removeFragment(tag: String,
                                            slideIn: Int = R.anim.slide_left,
                                            slideOut: Int = R.anim.slide_right) {
    this.beginTransaction()
        .disallowAddToBackStack()
        .setCustomAnimations(slideIn, slideOut)
        .remove(this.findFragmentByTag(tag))
        .commitNow()
}

internal fun FragmentManager.addFragment(containerViewId: Int,
                                         fragment: Fragment,
                                         tag: String,
                                         slideIn: Int = R.anim.slide_left,
                                         slideOut: Int = R.anim.slide_right) {
    this.beginTransaction().disallowAddToBackStack()
        .setCustomAnimations(slideIn, slideOut)
        .add(containerViewId, fragment, tag)
        .commit()
}*/
