package com.gzeinnumer.trainingdaggerpandeckt.network

import com.google.gson.annotations.SerializedName

//todo 18
data class ResponsePost(

	@field:SerializedName("id")
    var id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
)
