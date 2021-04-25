package com.example.simple_dictionary.search.presenter.model

data class SearchResultItem(
    val id: Int,
    val word: String,
    val meaning: String,
    val otherMeanings: List<String>,
    val imageUrl: String
)