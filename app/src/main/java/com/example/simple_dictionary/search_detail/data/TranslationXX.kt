package com.example.simple_dictionary.search_detail.data


import com.google.gson.annotations.SerializedName

data class TranslationXX(
    @SerializedName("note")
    val note: String,
    @SerializedName("text")
    val text: String
)