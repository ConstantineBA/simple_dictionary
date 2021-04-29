package com.example.simple_dictionary.core.mappers

import com.example.simple_dictionary.search.data.SearchResult
import com.example.simple_dictionary.search.presenter.model.SearchResultItem
import javax.inject.Inject

class SearchResultMapper @Inject constructor() {
    fun mapItem(searchResult: SearchResult): SearchResultItem {
        with(searchResult) {
            val firstMeaning = meanings.first()
            return SearchResultItem(
                id = firstMeaning.id,
                word = text,
                meaning = firstMeaning.translation.text,
                otherMeanings = searchResult.meanings.drop(1).map { it.translation.text },
                imageUrl = firstMeaning.previewUrl
            )
        }
    }
}