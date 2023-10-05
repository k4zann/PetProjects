package com.example.ablemovieapi2.respitory

import com.example.ablemovieapi2.data.Movie

interface MovieRepository {
    fun getMovieDetails(movieId: Long, callback: MovieCallback)

    interface MovieCallback {
        fun onMovieLoaded(movie: Movie?) {

        }
    }
}