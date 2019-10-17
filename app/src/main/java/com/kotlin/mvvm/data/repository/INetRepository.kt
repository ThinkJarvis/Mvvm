package com.kotlin.mvvm.data.repository

import com.kotlin.mvvm.data.model.Failure
import com.kotlin.mvvm.data.model.Either
import kotlinx.coroutines.Deferred
import retrofit2.Response


abstract class INetRepository<out T> {
    protected val apiService: T by lazy {
        createAPIService()
    }

    abstract fun createAPIService(): T

    protected suspend fun <T, R> request(
        deferred: Deferred<Response<T>>, transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            val response = deferred.await()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}