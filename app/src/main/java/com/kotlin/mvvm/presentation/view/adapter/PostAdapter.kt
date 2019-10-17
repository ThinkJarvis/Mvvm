package com.kotlin.mvvm.presentation.view.adapter

import com.kotlin.mvvm.R
import com.kotlin.mvvm.data.model.Post
import com.kotlin.mvvm.presentation.platform.BindAdapter

class PostAdapter : BindAdapter<Post>() {

    override fun getLayout() = R.layout.layout_item_second
}