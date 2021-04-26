package com.example.simple_dictionary.search.presenter

import com.example.simple_dictionary.core.base.BaseUiEvent

sealed class SearchUiEvent : BaseUiEvent() {

    data class InputSearchTextUiEvent(val inputSting: String) : SearchUiEvent()

    data class OnItemClickedUiEvent(val id: Int) : SearchUiEvent()

}