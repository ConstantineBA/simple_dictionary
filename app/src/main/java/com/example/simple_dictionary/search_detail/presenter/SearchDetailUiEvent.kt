package com.example.simple_dictionary.search_detail.presenter

import com.example.simple_dictionary.core.base.BaseUiEvent

sealed class SearchDetailUiEvent : BaseUiEvent() {

    class GetSearchDetails(val id: Int) : SearchDetailUiEvent()
}