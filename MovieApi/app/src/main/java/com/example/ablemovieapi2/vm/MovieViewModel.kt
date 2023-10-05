package com.example.ablemovieapi2.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ablemovieapi2.data.Movie
import com.example.ablemovieapi2.respitory.MovieRepository
import com.example.ablemovieapi2.respitory.MovieRepositoryImpl

class MovieViewModel: ViewModel() {
    private val movieRepository: MovieRepository = MovieRepositoryImpl()
    private val movieCallback: MovieRepository.MovieCallback = object : MovieRepository.MovieCallback {
        override fun onMovieLoaded(movie: Movie?) {
            _currentMovie.value = movie
            Log.d(TAG,"onMovieLoaded -> $movie")
        }
    }
    private val _currentMovie: MutableLiveData<Movie?> = MutableLiveData(null)

    val currentMovie: LiveData<Movie?> = _currentMovie

    fun loadMovie(movieId: Long){
        Log.d(TAG, "loadMovie -> $movieId")
        movieRepository.getMovieDetails(movieId, movieCallback)
    }
    companion object {
        private const val TAG = "MovieViewModel"
    }
}
