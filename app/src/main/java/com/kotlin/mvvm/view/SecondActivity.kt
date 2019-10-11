package com.kotlin.mvvm.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.mvvm.R
import com.kotlin.mvvm.adapter.PostAdapter
import com.kotlin.mvvm.databinding.SecondViewDataBinding
import com.kotlin.mvvm.exception.Failure
import com.kotlin.mvvm.exception.Failure.*
import com.kotlin.mvvm.extension.failure
import com.kotlin.mvvm.extension.observe
import com.kotlin.mvvm.extension.toast
import com.kotlin.mvvm.extension.viewModel
import com.kotlin.mvvm.model.Post
import com.kotlin.mvvm.model.PostFailure.NonExistentMovie
import com.kotlin.mvvm.viewmodel.SecondViewModel
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
        secondViewDataBinding =
            DataBindingUtil.setContentView<SecondViewDataBinding>(this, R.layout.activity_second)
        secondVM = viewModel {
            observe(posts, ::renderPostList)
            failure(failure, ::renderFailure)
        }
        secondViewDataBinding.secondViewModel = secondVM

        secondVM.loadingData()

        recycler_view.layoutManager = linearLayoutManager
        recycler_view.adapter = postAdapter
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