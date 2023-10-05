package com.example.ablemovieapi2.respitory

import com.example.ablemovieapi2.api.MovieApi
import com.example.ablemovieapi2.data.Images
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePostersRepositoryImpl : MoviePostersRepository {
    override fun getMoviePosters(movieId: Long, callback: MoviePostersRepository.MoviePostersCallback){
        MovieApi.INSTANCE.getMovieImages(movieId, API_KEY).enqueue(object: Callback<Images>{
            override fun onResponse(call: Call<Images>, response: Response<Images>) {
                if(response.isSuccessful) {
                    callback.onMoviePostersLoaded(response.body())
                } else {
                    callback.onMoviePostersLoaded(null)
                }
            }

            override fun onFailure(call: Call<Images>, t: Throwable) {
                callback.onMoviePostersLoaded(null)
            }

        })

    }

    companion object {
        private const val API_KEY = "670f7d72022da5eca8d5c94c8b80781f"
    }
}