package com.example.tinkoffschoolapp.presentation.change_post_type

import android.widget.TextView
import com.example.tinkoffschoolapp.R

class ChangePostTypeView(
    presenter: ChangePostTypeContract.Presenter,
    private val latestTV: TextView,
    private val hotTV: TextView,
    private val topTV: TextView
) : ChangePostTypeContract.View {

    init {
        latestTV.setOnClickListener { presenter.onPostTypeClicked(ChangePostTypeContract.PostType.LATEST) }
        hotTV.setOnClickListener { presenter.onPostTypeClicked(ChangePostTypeContract.PostType.HOT) }
        topTV.setOnClickListener { presenter.onPostTypeClicked(ChangePostTypeContract.PostType.TOP) }

        presenter.onViewReady(this)
    }

    override fun changePostType(postType: ChangePostTypeContract.PostType) {
        latestTV.setTextColor(latestTV.context.resources.getColor(R.color.secondaryDarkColor, null))
        hotTV.setTextColor(latestTV.context.resources.getColor(R.color.secondaryDarkColor, null))
        topTV.setTextColor(latestTV.context.resources.getColor(R.color.secondaryDarkColor, null))

        val currentView = when(postType) {
            ChangePostTypeContract.PostType.LATEST -> latestTV
            ChangePostTypeContract.PostType.HOT -> hotTV
            ChangePostTypeContract.PostType.TOP -> topTV
        }
        currentView.setTextColor(latestTV.context.resources.getColor(R.color.accentColor, null))
    }

}