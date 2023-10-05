package com.example.ablemovieapi2.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ablemovieapi2.data.Images
import com.example.ablemovieapi2.respitory.MoviePostersRepository
import com.example.ablemovieapi2.respitory.MoviePostersRepositoryImpl

class MovieImagesViewModel: ViewModel() {
    private val moviePostersRepository: MoviePostersRepository = MoviePostersRepositoryImpl()

    private val moviePostersCallback : MoviePostersRepository.MoviePostersCallback = object : MoviePostersRepository.MoviePostersCallback{
        override fun onMoviePostersLoaded(moviePosters: Images?) {
            _currentMoviePosters.value = moviePosters
        }
    }

    private val _currentMoviePosters: MutableLiveData<Images?> = MutableLiveData(null)
    val currentMoviePosters: LiveData<Images?> = _currentMoviePosters

    fun loadMovie(movieId: Long) {
        moviePostersRepository.getMoviePosters(movieId, moviePostersCallback)
    }
}