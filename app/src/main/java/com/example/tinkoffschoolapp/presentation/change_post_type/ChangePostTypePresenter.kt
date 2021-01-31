package com.example.tinkoffschoolapp.presentation.change_post_type

import androidx.lifecycle.ViewModel

class ChangePostTypePresenter(private val boundary: ChangePostTypeContract.Boundary) : ChangePostTypeContract.Presenter, ViewModel(){

    private lateinit var view: ChangePostTypeContract.View
    private var currentPostType : ChangePostTypeContract.PostType = ChangePostTypeContract.PostType.LATEST

    override fun onViewReady(view: ChangePostTypeContract.View) {
        this.view = view
        view.changePostType(currentPostType)
    }

    override fun onPostTypeClicked(postType: ChangePostTypeContract.PostType) {
        currentPostType = postType
        view.changePostType(postType)
        boundary.onPostTypeChanged(postType)
    }

}

