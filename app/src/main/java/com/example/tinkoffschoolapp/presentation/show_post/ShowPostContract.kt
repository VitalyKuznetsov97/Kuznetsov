package com.example.tinkoffschoolapp.presentation.show_post

import com.example.tinkoffschoolapp.domain.models.Post
import com.example.tinkoffschoolapp.presentation.change_post_type.ChangePostTypeContract

interface ShowPostContract {

    interface View {

        fun showPost(post: Post)

        fun setShowPreviousAllowed(allowed: Boolean)

        fun showLoading()

        fun hideLoading()

        fun showInfoText(text: String)

    }

    interface Presenter {

        fun onViewReady(view: View)

        fun onPreviousClicked()

        fun onNextClicked()

        fun onPostTypeChanged(postType: ChangePostTypeContract.PostType)
    }

}