package com.example.simple_dictionary.search_detail.data


import com.google.gson.annotations.SerializedName

data class AlternativeTranslation(
    @SerializedName("text")
    val text: String,
    @SerializedName("translation")
    val translation: Translation
)