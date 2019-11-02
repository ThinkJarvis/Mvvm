package com.kotlin.mvvm.data.repository

import com.kotlin.mvvm.data.NetworkHandler
import com.kotlin.walletservice.model.Either
import com.kotlin.walletservice.model.Failure
import com.kotlin.walletservice.model.Post
import com.kotlin.walletservice.remote.RetrofitFactory
import com.kotlin.walletservice.remote.RetrofitService


class PostRepository : INetRepository<RetrofitService>() {
    override fun createAPIService() =
        RetrofitFactory.createRetrofitService<RetrofitService>()

    fun getPosts(): Either<Failure, List<Post>> {
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