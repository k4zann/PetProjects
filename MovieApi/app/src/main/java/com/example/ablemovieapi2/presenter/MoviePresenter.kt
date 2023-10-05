package com.example.ablemovieapi2.presenter

import com.example.ablemovieapi2.presenter.view.MovieImagesView
import com.example.ablemovieapi2.presenter.view.MovieView

interface MoviePresenter {
    fun setMovieView(movieView: MovieView?)
    fun setMoviePostersView(moviePostersView: MovieImagesView?)
    fun onLoadClicked(movieId: Long)
}