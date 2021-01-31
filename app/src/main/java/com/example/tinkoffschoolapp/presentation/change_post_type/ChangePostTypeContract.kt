package com.example.tinkoffschoolapp.presentation.change_post_type

interface ChangePostTypeContract {

    interface View {

        fun changePostType(postType: PostType)

    }

    interface Presenter {

        fun onViewReady(view: View)

        fun onPostTypeClicked(postType: PostType)

    }

    interface Boundary {

        fun onPostTypeChanged(postType: ChangePostTypeContract.PostType)

    }

    enum class PostType {
        LATEST,
        HOT,
        TOP
    }

}
