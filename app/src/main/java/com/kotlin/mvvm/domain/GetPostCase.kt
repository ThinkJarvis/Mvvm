package com.kotlin.mvvm.domain

import com.kotlin.mvvm.data.factory.DataStoreFactory
import com.kotlin.mvvm.data.repository.PostRepository
import com.kotlin.mvvm.data.model.Failure
import com.kotlin.mvvm.data.model.Either
import com.kotlin.mvvm.domain.UseCase.None
import com.kotlin.mvvm.data.model.Post

class GetPostCase : UseCase<List<Post>, None>() {
    override suspend fun run(params: None): Either<Failure, List<Post>> =
        (DataStoreFactory.instance().getINetRepository(PostRepository::class.java.simpleName) as PostRepository)?.getPosts()

}