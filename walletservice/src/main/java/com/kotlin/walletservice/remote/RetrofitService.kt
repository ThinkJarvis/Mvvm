package com.kotlin.walletservice.remote

import com.kotlin.walletservice.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("/posts")
    fun getPosts(): Call<List<Post>>
}