package com.kotlin.mvvm.features.post

import com.kotlin.mvvm.api.NetRepository
import com.kotlin.mvvm.exception.Failure
import com.kotlin.mvvm.functional.Either
import com.kotlin.mvvm.interactor.UseCase
import com.kotlin.mvvm.interactor.UseCase.None
import com.kotlin.mvvm.model.Post

object GetPost : UseCase<List<Post>, None>() {
    override suspend fun run(params: None): Either<Failure, List<Post>> = NetRepository.getPosts()
}