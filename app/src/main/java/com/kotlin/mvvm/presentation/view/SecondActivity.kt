package com.kotlin.mvvm.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.mvvm.R
import com.kotlin.mvvm.presentation.view.adapter.PostAdapter
import com.kotlin.mvvm.databinding.SecondViewDataBinding
import com.kotlin.mvvm.data.model.Failure
import com.kotlin.mvvm.data.model.Failure.*
import com.kotlin.mvvm.presentation.extension.*
import com.kotlin.mvvm.data.model.Post
import com.kotlin.mvvm.data.model.PostFailure.NonExistentMovie
import com.kotlin.mvvm.presentation.viewmodel.SecondViewModel
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private lateinit var secondViewDataBinding: SecondViewDataBinding
    private lateinit var secondVM: SecondViewModel

    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private val postAdapter = PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondViewDataBinding = setContentView { R.layout.activity_second }
        secondVM = viewModel {
            observe(posts, ::renderPostList)
            failure(failure, ::renderFailure)
        }
        secondViewDataBinding.secondViewModel = secondVM

        secondVM.loadingData()

        recycler_view.run {
            layoutManager = linearLayoutManager
            adapter = postAdapter
        }
    }


    private fun renderPostList(posts: List<Post>?) {
        if (posts.isNullOrEmpty())
            renderFailure(NonExistentMovie())
        else
            postAdapter.collection = posts.orEmpty()


    }

    private fun renderFailure(failure: Failure?) {
        when (failure) {
            is NetworkConnection -> toast(R.string.failure_network_connection)
            is ServerError -> toast(R.string.failure_server_error)
            is NonExistentMovie -> toast(R.string.failure_post_non_existent)
        }
    }

}