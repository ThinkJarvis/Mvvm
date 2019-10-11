package com.kotlin.mvvm.adapter

import com.kotlin.mvvm.R
import com.kotlin.mvvm.model.Post

class PostAdapter : BindAdapter<Post>() {

    override fun getLayout() = R.layout.layout_item_second
}