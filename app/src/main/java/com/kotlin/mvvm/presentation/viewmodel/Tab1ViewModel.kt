package com.kotlin.mvvm.presentation.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.kotlin.mvvm.domain.GetPostCase
import com.kotlin.mvvm.domain.UseCase.None
import com.kotlin.mvvm.presentation.platform.CoroutineViewModel
import com.kotlin.walletservice.model.Failure
import com.kotlin.walletservice.model.Post
import com.orhanobut.logger.Logger


class Tab1ViewModel : CoroutineViewModel() {


    var posts = MutableLiveData<List<Post>>()

    val loading = ObservableBoolean(false)


    private val getPost by lazy {
        GetPostCase()
    }

    fun loadingData() {
        loading.set(true)
        loadPosts()
        shop("X") { Logger.i("买了一件衣服") }
    }

    fun loadPosts() = getPost(None()) { it.either(::handleFailure, ::handlePostList) }


    private fun handlePostList(posts: List<Post>) {
        loading.set(false)
        this.posts.value = posts
    }

    override fun handleFailure(failure: Failure) {
        super.handleFailure(failure)
        loading.set(false)
    }


    fun shop(girl: String, play: () -> Unit) {
        Logger.i("女朋友: $girl")
        play()
    }

    override fun onCleared() {
        super.onCleared()
        getPost.cancelJob()
    }
}