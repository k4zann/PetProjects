package com.example.ablemovieapi2.api

import com.example.ablemovieapi2.data.Images
import com.example.ablemovieapi2.data.Movie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/{movieId}") // getMovieDetails(5, "asdfsadfasfasdf") -> server.com/movie/5?api_key=asdfsadfasfasdf
    fun getMovieDetails(@Path("movieId") movieId: Long, @Query("api_key") apiKey: String): Call<Movie>

    @GET("movie/{movieId}/images")
    fun getMovieImages(@Path("movieId") movieId: Long, @Query("api_key") apiKey: String): Call<Images>

    companion object {
        val INSTANCE = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
            .create(MovieApi::class.java)
    }
}