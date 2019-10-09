package com.kotlin.mvvm.api

import android.util.Log
import com.kotlin.mvvm.exception.Failure
import com.kotlin.mvvm.exception.Failure.NetworkConnection
import com.kotlin.mvvm.exception.Failure.ServerError
import com.kotlin.mvvm.functional.Either
import com.kotlin.mvvm.functional.Either.Left
import  com.kotlin.mvvm.functional.Either.Right
import com.kotlin.mvvm.model.Post
import kotlinx.coroutines.Deferred
import retrofit2.Response

object NetRepository {

    private val service: RetrofitService by lazy {
        RetrofitFactory.createRetrofitService()
    }

    suspend fun getPosts(): Either<Failure, List<Post>> {
        return when (NetworkHandler.isConnected()) {
            true -> request(service.getPosts(), { it.map { it.toPosts() } }, emptyList())
            false -> Left(NetworkConnection)
        }
    }

    private suspend  fun <T, R> request(deferred: Deferred<Response<T>>, transform: (T) -> R, default: T): Either<Failure, R> {
        return try {
            val response = deferred.await()
            when (response.isSuccessful) {
                true -> Right(transform((response.body() ?: default)))
                false ->Left(ServerError)
            }
        } catch (exception: Throwable) {
            Left(ServerError)
        }
    }

}