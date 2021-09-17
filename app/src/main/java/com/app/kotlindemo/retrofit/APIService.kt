package com.app.kotlindemo.retrofit

import com.app.kotlindemo.model.RandomListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Pratik on 7/5/19.
 */
interface APIService {

    object ApiUtils {

        private const val BASE_URL = "https://randomuser.me/"

        val apiService: APIService
            get() = RetrofitClient.getClient(BASE_URL)!!.create(APIService::class.java)

    }

    @GET("api/")
    fun getUserList(@Query("page") page:Int,
                @Query("results") results:Int,
                    @Query("seed") seed:String): Call<RandomListResponse>
}