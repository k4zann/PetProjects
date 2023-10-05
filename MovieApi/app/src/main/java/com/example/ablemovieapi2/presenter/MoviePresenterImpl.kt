package com.example.ablemovieapi2.presenter

import com.example.ablemovieapi2.data.Images
import com.example.ablemovieapi2.data.Movie
import com.example.ablemovieapi2.respitory.MoviePostersRepository
import com.example.ablemovieapi2.respitory.MoviePostersRepositoryImpl
import com.example.ablemovieapi2.respitory.MovieRepository
import com.example.ablemovieapi2.respitory.MovieRepositoryImpl
import com.example.ablemovieapi2.presenter.view.MovieImagesView
import com.example.ablemovieapi2.presenter.view.MovieView

class MoviePresenterImpl : MoviePresenter{
    private val movieRepository: MovieRepository = MovieRepositoryImpl()
    private val moviePostersRepository: MoviePostersRepository = MoviePostersRepositoryImpl()
    private var movieView: MovieView? = null
    private var moviePostersView: MovieImagesView? = null

    private val movieCallback: MovieRepository.MovieCallback = object : MovieRepository.MovieCallback {
        override fun onMovieLoaded(movie: Movie?) {
            movieView?.setMovie(movie)
        }
    }
    private val moviePostersCallback : MoviePostersRepository.MoviePostersCallback = object : MoviePostersRepository.MoviePostersCallback{
        override fun onMoviePostersLoaded(moviePosters: Images?) {
            moviePostersView?.setMovieImage(moviePosters)
        }
    }
    override fun setMovieView(movieView: MovieView?) {
        this.movieView = movieView
    }

    override fun setMoviePostersView(moviePostersView: MovieImagesView?) {
        this.moviePostersView = moviePostersView
    }

    override fun onLoadClicked(movieId: Long) {
        movieRepository.getMovieDetails(movieId, movieCallback)
        moviePostersRepository.getMoviePosters(movieId, moviePostersCallback)

    }
}