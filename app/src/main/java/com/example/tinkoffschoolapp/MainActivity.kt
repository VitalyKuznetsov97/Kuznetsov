package com.example.tinkoffschoolapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.tinkoffschoolapp.data.repository.ApiRepository
import com.example.tinkoffschoolapp.domain.data.SimpleCallback
import com.example.tinkoffschoolapp.domain.data.models.Post
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private val urlList = mutableListOf<String>()
    private var counter = 0
    private val simpleCallback = object : SimpleCallback<List<Post>> {
        override fun onSuccess(result: List<Post>) {
            if (result.isEmpty()) {
                onFailure(Exception("No posts"))
                return
            }

            urlList.clear()
            urlList.addAll(result.map { it.gifURL })
            loadUrl(urlList[0])

            info_tv.visibility = View.GONE
            loading_pb.visibility = View.GONE
        }

        override fun onFailure(exception: Exception) {
            info_tv.visibility = View.VISIBLE
            info_tv.text = exception.message
            loading_pb.visibility = View.GONE
        }

    }

    override fun onResume() {
        super.onResume()

        val apiRepository = ApiRepository()

        apiRepository.getLatestPosts(0, simpleCallback)
        latest_tv.setOnClickListener {
            setState(it as TextView)
            apiRepository.getLatestPosts(0, simpleCallback)
        }
        hot_tv.setOnClickListener {
            setState(it as TextView)
            apiRepository.getHotPosts(0, simpleCallback)
        }
        top_tv.setOnClickListener {
            setState(it as TextView)
            apiRepository.getTopPosts(0, simpleCallback)
        }

        previous_post_tv.setOnClickListener { loadUrl(urlList[prev()]) }
        next_post_tv.setOnClickListener { loadUrl(urlList[next()]) }

        latest_tv.callOnClick()
    }

    private fun loadUrl(url: String) {
        Glide
            .with(this)
            .asGif()
            .load(url)
            .fitCenter()
            .into(image_view)
    }

    private fun prev(): Int {
        if (--counter < 0)
            counter = urlList.lastIndex
        return counter
    }

    private fun next(): Int {
        if (++counter >= urlList.size)
            counter = 0
        return counter
    }

    private fun setState(currentView: TextView) {
        latest_tv.setTextColor(resources.getColor(R.color.secondaryDarkColor, null))
        hot_tv.setTextColor(resources.getColor(R.color.secondaryDarkColor, null))
        top_tv.setTextColor(resources.getColor(R.color.secondaryDarkColor, null))

        currentView.setTextColor(resources.getColor(R.color.accentColor, null))

        loading_pb.visibility = View.VISIBLE
    }

}