package com.example.tinkoffschoolapp.presentation.change_post_type

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChangePostTypePresenterFactory(private val boundary: ChangePostTypeContract.Boundary) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ChangePostTypePresenter::class.java) -> ChangePostTypePresenter(boundary)
            else -> throw UnsupportedOperationException("Unknown class ${modelClass.simpleName}")
        } as T
    }
}