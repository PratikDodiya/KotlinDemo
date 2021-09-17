package com.app.kotlindemo.model
import com.google.gson.annotations.SerializedName

data class RandomListResponse (

	@SerializedName("results") val results : ArrayList<Results>,
	@SerializedName("info") val info : Info
)