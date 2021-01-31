package com.example.tinkoffschoolapp.domain.data

import com.example.tinkoffschoolapp.domain.data.models.Post
import java.lang.Exception

interface PostRepository {

    fun getLatestPosts(page: Int, simpleCallback: SimpleCallback<List<Post>>)

    fun getHotPosts(page: Int, simpleCallback: SimpleCallback<List<Post>>)

    fun getTopPosts(page: Int, simpleCallback: SimpleCallback<List<Post>>)

}

interface SimpleCallback<T> {

    fun onSuccess(result: T)

    fun onFailure(exception: Exception)

}
