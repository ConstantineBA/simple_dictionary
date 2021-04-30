package com.example.simple_dictionary.search.presenter

import com.example.simple_dictionary.core.base.BaseUiModel
import com.example.simple_dictionary.search.presenter.model.SearchResultItem

sealed class SearchUiModel : BaseUiModel() {

    data class SearchResultUiModel(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val isRefresh: Boolean = false,
        val results: List<SearchResultItem> = emptyList()
    ) : SearchUiModel() {

        fun toLoading(): SearchResultUiModel = copy(
            isLoading = true,
            isError = false,
            isRefresh = false
        )

        fun toError(): SearchResultUiModel = copy(
            isLoading = false,
            isError = true,
            isRefresh = false
        )

        fun toRefresh(): SearchResultUiModel = copy(
            isLoading = false,
            isError = false,
            isRefresh = true
        )

        fun isContent(): Boolean = !isLoading && !isError
    }

    data class GoToSearchDetail(val id: Int) : SearchUiModel()
}