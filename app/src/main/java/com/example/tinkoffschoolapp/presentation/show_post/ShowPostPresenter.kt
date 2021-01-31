package com.example.tinkoffschoolapp.presentation.show_post

import androidx.lifecycle.ViewModel
import com.example.tinkoffschoolapp.domain.data.PostRepository
import com.example.tinkoffschoolapp.domain.data.SimpleCallback
import com.example.tinkoffschoolapp.domain.models.Post
import com.example.tinkoffschoolapp.presentation.change_post_type.ChangePostTypeContract

class ShowPostPresenter(private val postRepository: PostRepository) : ShowPostContract.Presenter, ViewModel() {

    private lateinit var view: ShowPostContract.View
    private var currentPostType: ChangePostTypeContract.PostType = ChangePostTypeContract.PostType.LATEST

    private val postList = mutableListOf<Post>()
    private var currentPostCounter = 0
    private var lastLoadedPage = -1

    private val simpleCallback = object : SimpleCallback<List<Post>> {
        override fun onSuccess(result: List<Post>) {
            if (result.isEmpty()) {
                onFailure(Exception("No posts"))
                return
            }

            postList.addAll(result)
            showPost()
        }

        override fun onFailure(exception: Exception) {
            postList.clear()
            exception.message?.let { showInfo(it) }
        }
    }

    override fun onViewReady(view: ShowPostContract.View) {
        this.view = view
        showPost()
    }

    override fun onPreviousClicked() {
        if (currentPostCounter == 0)
            return

        currentPostCounter--
        showPost()
    }

    override fun onNextClicked() {
        currentPostCounter++
        showPost()
    }

    override fun onPostTypeChanged(postType: ChangePostTypeContract.PostType) {
        if (currentPostType == postType)
            return

        currentPostType = postType
        postList.clear()
        currentPostCounter = 0
        lastLoadedPage = -1

        showPost()
    }

    private fun loadPosts() {
        when (currentPostType) {
            ChangePostTypeContract.PostType.LATEST -> {
                postRepository.getLatestPosts(++lastLoadedPage, simpleCallback)
            }
            ChangePostTypeContract.PostType.HOT -> {
                postRepository.getHotPosts(++lastLoadedPage, simpleCallback)
            }
            ChangePostTypeContract.PostType.TOP -> {
                postRepository.getTopPosts(++lastLoadedPage, simpleCallback)
            }
        }
    }

    private fun showInfo(text: String) {
        view.hideLoading()
        view.showInfoText(text)
    }

    private fun showPost() {
        if (currentPostCounter >= postList.size) {
            loadPosts()
            return
        }

        view.hideLoading()
        view.showPost(postList[currentPostCounter])
        view.setShowPreviousAllowed(currentPostCounter != 0)
    }

}
