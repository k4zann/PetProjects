package com.example.ablemovieapi2.respitory

import android.util.Log
import android.widget.Toast
import com.example.ablemovieapi2.api.MovieApi
import com.example.ablemovieapi2.data.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepositoryImpl : MovieRepository{
    override fun getMovieDetails(movieId: Long, callback: MovieRepository.MovieCallback){
        MovieApi.INSTANCE.getMovieDetails(movieId, API_KEY).enqueue(object: Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if(response.isSuccessful) {
                   callback.onMovieLoaded(response.body())
                    Log.d(TAG, "getMovieDetails - ${response.body()}")
                }else {
                    Log.d(TAG, "getMovieDetails - ${response.body()}")
                    callback.onMovieLoaded(null)
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e(TAG, "getMovieDetails - ${t.message}")
                callback.onMovieLoaded(null)
            }

        })


    }

    companion object {
        private const val API_KEY = "670f7d72022da5eca8d5c94c8b80781f"
        private const val TAG = "MovieRepositoryImpl"
    }
}