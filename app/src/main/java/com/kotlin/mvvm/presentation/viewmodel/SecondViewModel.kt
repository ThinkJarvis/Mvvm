package com.kotlin.mvvm.presentation.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.kotlin.mvvm.data.model.Failure
import com.kotlin.mvvm.domain.GetPostCase
import com.kotlin.mvvm.domain.UseCase.None
import com.kotlin.mvvm.data.model.Post
import com.kotlin.mvvm.presentation.platform.CoroutineViewModel


class SecondViewModel : CoroutineViewModel() {


    var posts = MutableLiveData<List<Post>>()

    val loading = ObservableBoolean(false)


    private val getPost by lazy {
        GetPostCase()
    }

    fun loadingData() {
        loading.set(true)
        loadPosts()
        shop("X") { Log.e("wjq", "买了一件衣服") }
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
        Log.e("wjq", "女朋友: $girl")
        play()
    }
}