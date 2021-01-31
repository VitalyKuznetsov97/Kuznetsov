package com.example.tinkoffschoolapp.data.api

import com.example.tinkoffschoolapp.data.dto.PostListDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET(LATEST_POSTS)
    fun getLatestPosts(@Path("page") page: Int) : Call<PostListDto>

    @GET(HOT_POSTS)
    fun getHotPosts(@Path("page") page: Int) : Call<PostListDto>

    @GET(TOP_POSTS)
    fun getTopPosts(@Path("page") page: Int) : Call<PostListDto>

    companion object {

        const val BASE_URL = "https://developerslife.ru"

        private const val LATEST_POSTS = "/latest/{page}?json=true"
        private const val HOT_POSTS = "/hot/{page}?json=true"
        private const val TOP_POSTS = "/top/{page}?json=true"

    }

}
