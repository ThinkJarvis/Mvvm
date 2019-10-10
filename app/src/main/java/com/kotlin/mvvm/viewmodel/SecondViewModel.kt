package com.kotlin.mvvm.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import com.kotlin.mvvm.exception.Failure
import com.kotlin.mvvm.extension.empty
import com.kotlin.mvvm.features.post.GetPost
import com.kotlin.mvvm.interactor.UseCase.None
import com.kotlin.mvvm.model.Post
import com.kotlin.mvvm.platform.CoroutineViewModel


class SecondViewModel : CoroutineViewModel() {

    private var failure: String = String.empty()
    private var posts = mutableListOf<Post>()

    val loading = ObservableBoolean(false)


    fun loadingData() {
        loading.set(true)
        loadPosts()

        shop("X"){Log.e("wjq","买了一件衣服")}
    }

    fun loadPosts() = GetPost(None()) { it.either(::handleFailure, ::handlePostList) }


    private fun handleFailure(failure: Failure) {
        loading.set(false)
        this.failure = failure.toString()
        Log.e("wjq", "${failure}")
    }

    private fun handlePostList(posts: List<Post>) {
        loading.set(false)
        this.posts = posts.toMutableList()
        posts.forEach {
            Log.e("wjq", "Mutable List Elements:${it.title}")
        }
    }


    fun shop(girl : String, play:()-> Unit) {
        Log.e("wjq","女朋友: $girl")
        play()
    }
}