package com.kotlin.mvvm.presentation.view.adapter

import com.kotlin.mvvm.R
import com.kotlin.mvvm.presentation.platform.BindAdapter
import com.kotlin.walletservice.model.Post

class PostAdapter : BindAdapter<Post>() {

    override fun getLayout() = R.layout.layout_item_second
}