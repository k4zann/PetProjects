package com.example.ablemovieapi2.data

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("file_path")val filePath: String?
) {
}