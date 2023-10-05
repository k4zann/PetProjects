package com.example.ablemovieapi2.data

import android.media.Image
import android.widget.ImageView
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("title")val title: String,
    @SerializedName("release_date")val releaseDate: String,
    @SerializedName("budget")val budget: Int,
//    @SerializedName("poster_path")val poster: String?
) {

}