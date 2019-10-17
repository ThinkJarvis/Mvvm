package com.kotlin.mvvm.data.repository

import com.kotlin.mvvm.remote.NetworkHandler
import com.kotlin.mvvm.remote.RetrofitFactory
import com.kotlin.mvvm.remote.RetrofitService
import com.kotlin.mvvm.data.model.Failure
import com.kotlin.mvvm.data.model.Either
import com.kotlin.mvvm.data.model.Post

class PostRepository : INetRepository<RetrofitService>() {
    override fun createAPIService() =
        RetrofitFactory.createRetrofitService<RetrofitService>()

    suspend fun getPosts(): Either<Failure, List<Post>> {
        return when (NetworkHandler.isConnected()) {
            true -> request(
                apiService.getPosts(),
                { it.map { it.toPosts() } },
                emptyList()
            )
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

}