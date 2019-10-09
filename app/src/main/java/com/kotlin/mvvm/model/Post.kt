package com.kotlin.mvvm.model

import com.kotlin.mvvm.extension.empty

data class Post(var userId: String, var id: String, var title: String, var body: String) {


    companion object {
        fun empty() = Post(String.empty(), String.empty(), String.empty(), String.empty())
    }

    fun toPosts() = Post(userId, id, title, body)
}