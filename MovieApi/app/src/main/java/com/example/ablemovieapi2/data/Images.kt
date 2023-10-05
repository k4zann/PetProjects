package com.example.ablemovieapi2.data

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("backdrops")
    val backdrops: List<Image>
) {

}