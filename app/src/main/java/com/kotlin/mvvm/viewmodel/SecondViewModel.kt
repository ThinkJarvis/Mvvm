package com.kotlin.mvvm.viewmodel

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import com.kotlin.mvvm.api.RetrofitFactory
import com.kotlin.mvvm.exception.Failure
import com.kotlin.mvvm.extension.empty
import com.kotlin.mvvm.features.post.GetPost
import com.kotlin.mvvm.interactor.UseCase.None
import com.kotlin.mvvm.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SecondViewModel : BaseObservable() {
    private var failure: String = String.empty()

    private var posts = mutableListOf<Post>()


    val loading = ObservableBoolean(false)


    fun loadingData() {
        Log.e("wjq", "2222222222222222222222222222222")
        loading.set(true)

        loadPosts()

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
}