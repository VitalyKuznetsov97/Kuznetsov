package com.example.tinkoffschoolapp.presentation.show_post

import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.tinkoffschoolapp.domain.models.Post

class ShowPostView(
    private val presenter: ShowPostContract.Presenter,
    private val postIV: ImageView,
    private val infoTV: TextView,
    private val loadingPB: View,
    private val previousPostV: View,
    nextPostV: View
) : ShowPostContract.View {

    private var onGlobalLayoutListener : ViewTreeObserver.OnGlobalLayoutListener? = null

    init {
        previousPostV.setOnClickListener { presenter.onPreviousClicked() }
        nextPostV.setOnClickListener { presenter.onNextClicked() }
        presenter.onViewReady(this)
    }

    override fun showPost(post: Post) {
        postIV.visibility = View.GONE
        infoTV.visibility = View.GONE
        loadingPB.visibility = View.VISIBLE

        Glide
            .with(postIV.context)
            .asGif()
            .load(post.gifURL)
            .fitCenter()
            .listener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<GifDrawable>?, isFirstResource: Boolean): Boolean {
                    postIV.visibility = View.VISIBLE
                    loadingPB.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(resource: GifDrawable?, model: Any?, target: Target<GifDrawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    postIV.visibility = View.VISIBLE
                    loadingPB.visibility = View.GONE
                    return false
                }
            })
            .into(postIV)
    }

    override fun setShowPreviousAllowed(allowed: Boolean) {
        previousPostV.isEnabled = allowed
    }

    override fun showLoading() {
        postIV.visibility = View.GONE
        infoTV.visibility = View.GONE
        loadingPB.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingPB.visibility = View.GONE
    }

    override fun showInfoText(text: String) {
        postIV.visibility = View.GONE
        infoTV.visibility = View.VISIBLE
        infoTV.text = text
    }

}
