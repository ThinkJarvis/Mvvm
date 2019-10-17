package com.kotlin.mvvm.remote


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    const val BASE_URL = "https://jsonplaceholder.typicode.com"

    inline fun <reified T> createRetrofitService(): T =
        createRetrofit().create(getRetrofitService<T>())


    fun createRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(getClient())
        .build()

    inline fun <reified T> getRetrofitService(): Class<T> = T::class.java


    private fun getClient(): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(LoggerInterceptor()).build()
}