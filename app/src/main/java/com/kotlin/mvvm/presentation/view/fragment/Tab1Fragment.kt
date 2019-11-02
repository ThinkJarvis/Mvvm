package com.kotlin.mvvm.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.mvvm.R
import com.kotlin.mvvm.databinding.Tab1DataBinding
import com.kotlin.mvvm.presentation.view.adapter.PostAdapter
import com.kotlin.mvvm.presentation.viewmodel.Tab1ViewModel
import com.kotlin.walletservice.extension.inflate
import com.kotlin.walletservice.extension.observe
import com.kotlin.walletservice.extension.failure
import com.kotlin.walletservice.extension.viewModel
import com.kotlin.walletservice.model.Failure
import com.kotlin.walletservice.model.Post
import com.kotlin.walletservice.model.PostFailure
import kotlinx.android.synthetic.main.fragment_tab1.*

class Tab1Fragment : Fragment() {

    companion object {
        val TAG = Tab1Fragment::class.java.simpleName
    }

    private lateinit var tab1DataBinding: Tab1DataBinding
    private lateinit var tab1ViewModel: Tab1ViewModel

    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private val postAdapter = PostAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        tab1DataBinding = inflate(inflater, R.layout.fragment_tab1, container)

        tab1ViewModel = viewModel {
            observe(posts, ::renderPostList)
            failure(failure, ::renderFailure)
        }

        tab1DataBinding.tab1ViewModel = tab1ViewModel

        postAdapter.onItemClickListener = handleClick()

        return tab1DataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.run {
            layoutManager = linearLayoutManager
            adapter = postAdapter
        }
        tab1ViewModel.loadingData()
    }

    private fun <T> handleClick() = { _: T, _: View, position: Int ->
        Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
    }


    private fun renderPostList(posts: List<Post>?) {
        if (posts.isNullOrEmpty())
            renderFailure(PostFailure.NonExistentMovie())
        else
            postAdapter.collection = posts.orEmpty()


    }

    private fun renderFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> Toast.makeText(
                context,
                R.string.failure_network_connection,
                Toast.LENGTH_SHORT
            ).show()
            is Failure.ServerError -> Toast.makeText(
                context,
                R.string.failure_server_error,
                Toast.LENGTH_SHORT
            ).show()
            is PostFailure.NonExistentMovie -> Toast.makeText(
                context,
                R.string.failure_post_non_existent,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}