package com.app.kotlindemo.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.Uri
import android.text.Editable
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.kotlindemo.App
import com.app.kotlindemo.R
import com.app.kotlindemo.utils.AppConstants.BLUR_RADIUS
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.google.gson.reflect.TypeToken
import jp.wasabeef.glide.transformations.BlurTransformation
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * Created by Pratik on 8/5/19.
 */
fun RecyclerView.setManager(
    isItHorizontal: Boolean = false,
    isItGrid: Boolean = false,
    spanCount: Int = 2
) {
    if (isItGrid)
        this.layoutManager = GridLayoutManager(this.context, spanCount)
    else {
        if (isItHorizontal)
            this.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
        else
            this.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
    }


}

/**
 * Set an onclick listener
 */
fun View.click(block: () -> Unit) = setOnClickListener { block.invoke() }


var progressDialog: Dialog? = null
fun Context.launchProgress() {
    progressDialog = Dialog(this)
    progressDialog?.setContentView(R.layout.progress_dialog)
    progressDialog?.setCancelable(false)
    progressDialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    progressDialog?.setCanceledOnTouchOutside(false)
    progressDialog?.show()
}

fun Fragment.launchProgress() {
    this.activity?.launchProgress()
}

fun disposeProgress() {
    if (progressDialog != null)
        progressDialog?.dismiss()
}

fun getRequestJSONBody(value: String): RequestBody =
    RequestBody.create(MediaType.parse("application/json"), value)

fun ImageView.loadUrl(url: String, placeholder: Int) {
    Glide.with(context).load(url).apply(RequestOptions().placeholder(placeholder)).into(this)
}

fun ImageView.loadURI(uri: Uri, placeholder: Int) {
    Glide.with(context).load(uri)
        .apply(RequestOptions().transform(CenterCrop()).placeholder(placeholder)).into(this)
}

fun ImageView.loadUrlRounded(url: String? = "", placeholder: Int) {
    Glide.with(context).load(url).apply(RequestOptions().circleCrop().placeholder(placeholder)).into(this)
}

fun ImageView.loadDrawableRounded(placeholder: Int) {
    Glide.with(context).load(placeholder).apply(RequestOptions().circleCrop().placeholder(placeholder)).into(this)
}

/*ImageView - Blurred Image
* 1st Parameter - resourceId
* 2nd Parameter - placeholder*/
fun ImageView.loadDrawableBlurred(resourceID: Int, placeholder: Int) {
    Glide.with(context).load(resourceID)
        .apply(RequestOptions().placeholder(placeholder))
        .apply(RequestOptions().transform(BlurTransformation(BLUR_RADIUS)))
        .into(this)
}

fun Context.toast(message: CharSequence, duration: Int) =
    Toast.makeText(this, message, duration).show()

fun String.toast() =
    Toast.makeText(App.application, this, Toast.LENGTH_LONG).show()

fun String.isValidEmail(): Boolean = this.isNotEmpty() &&
        Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun hideKeyboard(context: Context, view: View?) {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun isOnline(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = connectivityManager.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}

// TAG - classname prints in main method only but can't prints in retrofit inner methods or others
val Any.TAG: String
    get() {
//        val tag = javaClass.simpleName
//        return if (tag.length <= 23) tag else tag.substring(0, 23)
        return if (!javaClass.isAnonymousClass) {
            val name = javaClass.simpleName
            if (name.length <= 23) name else name.substring(0, 23)// first 23 chars
        } else {
            val name = javaClass.name
            if (name.length <= 23) name else name.substring(name.length - 23, name.length)// last 23 chars
        }
    }

// TAG() - classname prints in main method only but can't prints in retrofit inner methods or others
/*
inline fun <reified T> T.TAG(): String = T::class.java.simpleName*/

inline fun FragmentManager.addFragmentTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, tag: String) {
    supportFragmentManager.addFragmentTransaction { add(frameId, fragment, tag) }
}

inline fun FragmentManager.replaceFragmentTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, tag: String) {
    supportFragmentManager.replaceFragmentTransaction { replace(frameId, fragment, tag) }
}

/*
* genericType - Return type object
* */
inline fun <reified T> genericType() = object : TypeToken<T>() {}.type

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

/*
* Convert in String for EditText value
*/
fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

/**
 * Wrapping try/catch to ignore catch block
 */
inline fun <T> justTry(block: () -> T) = try {
    block()
} catch (e: Throwable) {
}