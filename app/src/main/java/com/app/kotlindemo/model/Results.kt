package com.app.kotlindemo.model
import com.google.gson.annotations.SerializedName

data class Results (

	@SerializedName("gender") val gender : String,
	@SerializedName("name") val name : Name,
	@SerializedName("email") val email : String,
	@SerializedName("phone") val phone : String,
	@SerializedName("cell") val cell : String,
	@SerializedName("picture") val picture : Picture,
	@SerializedName("nat") val nat : String
)