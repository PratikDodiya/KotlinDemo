package com.app.kotlindemo.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.kotlindemo.utils.CommonUtil

abstract class BaseActivity : AppCompatActivity() {

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun hideProgress() {
        progressDialog?.let { if (it.isShowing) it.cancel() }
    }

    fun showProgress() {
        hideProgress()
        progressDialog = CommonUtil.showLoadingDialog(this)
    }
}
