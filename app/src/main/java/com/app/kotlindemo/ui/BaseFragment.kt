package com.app.kotlindemo.ui

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.app.kotlindemo.ui.BaseActivity
import com.app.kotlindemo.utils.CommonUtil

/**
 * Created by Pratik on 8/5/19.
 */
abstract class BaseFragment : Fragment() {

    private var parentActivity: BaseActivity? = null
    private var progressDialog: ProgressDialog? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.parentActivity = activity
//            activity?.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setUp()
    }

    fun hideProgress() {
        if (progressDialog != null && progressDialog?.isShowing!!) {
            progressDialog?.cancel()
        }
    }

    fun showProgress() {
        hideProgress()
        progressDialog = CommonUtil.showLoadingDialog(this.context)
    }

    fun getBaseActivity() = parentActivity
}