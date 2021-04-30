package com.example.simple_dictionary.search_detail.presenter

import com.example.simple_dictionary.core.base.BaseUiModel
import com.example.simple_dictionary.search_detail.presenter.model.*

data class SearchDetailUiModel(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val transcription: TranscriptionItem? = null,
    val partOfSpeech: PartOfSpeechItem? = null,
    val definition: DefinitionItem? = null,
    val translations: List<TranslationItem>? = null,
    val examples: List<ExampleItem>? = null,
) : BaseUiModel() {

    fun toLoading() = SearchDetailUiModel(isLoading = true, isError = false)

    fun toError() = SearchDetailUiModel(isLoading = false, isError = true)

    fun isContent() = !isLoading && !isError
}