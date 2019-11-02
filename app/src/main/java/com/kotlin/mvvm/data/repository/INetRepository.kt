package com.kotlin.mvvm.data.repository


import com.kotlin.walletservice.model.Either
import com.kotlin.walletservice.model.Failure
import retrofit2.Call


abstract class INetRepository<out T> {
    protected val apiService: T by lazy {
        createAPIService()
    }

    abstract fun createAPIService(): T

    protected fun <T, R> request(
        call: Call<T>, transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}