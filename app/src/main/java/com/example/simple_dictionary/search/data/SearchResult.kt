package com.example.simple_dictionary.search.data

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("meanings")
    val meanings: List<Meaning>,
    @SerializedName("text")
    val text: String
)