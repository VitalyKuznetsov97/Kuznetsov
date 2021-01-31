package com.example.tinkoffschoolapp.presentation.show_post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tinkoffschoolapp.domain.data.PostRepository

class ShowPostPresenterFactory(private val postRepository: PostRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ShowPostPresenter::class.java) -> ShowPostPresenter(postRepository)
            else -> throw UnsupportedOperationException("Unknown class ${modelClass.simpleName}")
        } as T
    }
}