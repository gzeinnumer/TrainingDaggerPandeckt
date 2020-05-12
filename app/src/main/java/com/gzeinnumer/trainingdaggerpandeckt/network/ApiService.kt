package com.gzeinnumer.trainingdaggerpandeckt.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//todo 19
interface ApiService {

    //https://jsonplaceholder.typicode.com/posts?userId=1
    @GET("posts")
    fun getPost(
        @Query("userId") id: Int
    ): Call<List<ResponsePost>>
}