package com.app.kotlindemo.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.kotlindemo.R
import com.app.kotlindemo.adapter.RandomUserListAdapter
import com.app.kotlindemo.interfaces.RecyclerItemClickListener
import com.app.kotlindemo.model.RandomListResponse
import com.app.kotlindemo.model.Results
import com.app.kotlindemo.retrofit.APIService
import com.app.kotlindemo.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), RecyclerItemClickListener {

    /*private val apiService by lazy {
        APIService.create()
    }*/

    //Variable declaration
    var mAPIService: APIService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeView()
    }


    private fun initializeView() {
        recyclerView.setManager()
        mAPIService = APIService.ApiUtils.apiService
        callAPI()
    }

    private fun setAdapter(results: ArrayList<Results>) {
        val adapter = RandomUserListAdapter(results, this)
        recyclerView.adapter = adapter
    }

    private fun callAPI() {
        launchProgress()
        val call = mAPIService?.getUserList(0, 20, "")

        call?.enqueue(object : Callback<RandomListResponse> {
            override fun onResponse(call: Call<RandomListResponse>, response: Response<RandomListResponse>) {
                if (response.code() == 200) {
                    Log.e(TAG, "=====onResponse====")

                    disposeProgress()
                    if (response.body()?.results.isNullOrEmpty().not()) {

                        val arrayList: ArrayList<Results> = response.body()?.results!!
                        Log.d(TAG, "=====size====" + arrayList.size)
                        setAdapter(arrayList)
                    }
                }
            }

            override fun onFailure(call: Call<RandomListResponse>, t: Throwable) {
                disposeProgress()
                Log.e(TAG, "=====onFailure====$t")
            }
        })
    }

    override fun onItemClickEvent(view: View, position: Int, type: Int) {
        "Position : $position".toast()
    }
}
