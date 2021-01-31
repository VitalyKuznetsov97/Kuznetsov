package com.example.tinkoffschoolapp.data.repository

import androidx.lifecycle.ViewModel
import com.example.tinkoffschoolapp.data.api.Api
import com.example.tinkoffschoolapp.data.dto.PostListDto
import com.example.tinkoffschoolapp.domain.data.PostRepository
import com.example.tinkoffschoolapp.domain.data.SimpleCallback
import com.example.tinkoffschoolapp.domain.data.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class ApiRepository : PostRepository, ViewModel() {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: Api = retrofit.create(Api::class.java)

    override fun getLatestPosts(page: Int, simpleCallback: SimpleCallback<List<Post>>) {
        api.getLatestPosts(page).enqueue(callback(simpleCallback))
    }

    override fun getHotPosts(page: Int, simpleCallback: SimpleCallback<List<Post>>) {
        api.getHotPosts(page).enqueue(callback(simpleCallback))
    }

    override fun getTopPosts(page: Int, simpleCallback: SimpleCallback<List<Post>>) {
        api.getTopPosts(page).enqueue(callback(simpleCallback))
    }

    private fun callback(simpleCallback: SimpleCallback<List<Post>>): Callback<PostListDto> {
        return object : Callback<PostListDto> {
            override fun onResponse(call: Call<PostListDto>, response: Response<PostListDto>) {
                if (!response.isSuccessful) {
                    simpleCallback.onFailure(Exception(response.message()))
                    return
                }

                val result = response.body()?.result?.map { Post(it.gifURL) } ?: return

                simpleCallback.onSuccess(result)
            }

            override fun onFailure(call: Call<PostListDto>, t: Throwable) {
                simpleCallback.onFailure(Exception(t))
            }

        }
    }

}
