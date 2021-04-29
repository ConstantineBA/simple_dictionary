package com.example.simple_dictionary.search_detail.data


import com.google.gson.annotations.SerializedName

data class Definition(
    @SerializedName("soundUrl")
    val soundUrl: String,
    @SerializedName("text")
    val text: String
)