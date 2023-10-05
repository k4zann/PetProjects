package com.example.ablemovieapi2.respitory

import com.example.ablemovieapi2.data.Image
import com.example.ablemovieapi2.data.Images


interface MoviePostersRepository {
    fun getMoviePosters(movieId: Long, callback: MoviePostersCallback)

    interface MoviePostersCallback {
        fun onMoviePostersLoaded (moviePosters: Images?) {

        }
    }
}