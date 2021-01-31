package com.example.tinkoffschoolapp.domain

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tinkoffschoolapp.R
import com.example.tinkoffschoolapp.data.repository.ApiRepository
import com.example.tinkoffschoolapp.presentation.change_post_type.ChangePostTypeContract
import com.example.tinkoffschoolapp.presentation.change_post_type.ChangePostTypePresenter
import com.example.tinkoffschoolapp.presentation.change_post_type.ChangePostTypePresenterFactory
import com.example.tinkoffschoolapp.presentation.change_post_type.ChangePostTypeView
import com.example.tinkoffschoolapp.presentation.show_post.ShowPostContract
import com.example.tinkoffschoolapp.presentation.show_post.ShowPostPresenter
import com.example.tinkoffschoolapp.presentation.show_post.ShowPostPresenterFactory
import com.example.tinkoffschoolapp.presentation.show_post.ShowPostView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ChangePostTypeContract.Boundary {

    private lateinit var showPostPresenter: ShowPostContract.Presenter
    private lateinit var changePostTypePresenter: ChangePostTypeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        showPostPresenter = ViewModelProvider(
            this,
            ShowPostPresenterFactory(ApiRepository())
        ).get(ShowPostPresenter::class.java)
        ShowPostView(
            showPostPresenter,
            image_view,
            info_tv,
            loading_pb,
            previous_post_button,
            next_post_button
        )

        changePostTypePresenter = ViewModelProvider(
            this,
            ChangePostTypePresenterFactory(this)
        ).get(ChangePostTypePresenter::class.java)
        ChangePostTypeView(
            changePostTypePresenter,
            latest_tv,
            hot_tv,
            top_tv
        )

    }

    override fun onPostTypeChanged(postType: ChangePostTypeContract.PostType) {
        showPostPresenter.onPostTypeChanged(postType)
    }

}