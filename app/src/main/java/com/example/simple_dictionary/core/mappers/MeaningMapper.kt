package com.example.simple_dictionary.core.mappers

import com.example.simple_dictionary.search_detail.data.Definition
import com.example.simple_dictionary.search_detail.data.Example
import com.example.simple_dictionary.search_detail.data.MeaningResponse
import com.example.simple_dictionary.search_detail.data.MeaningsWithSimilarTranslation
import com.example.simple_dictionary.search_detail.presenter.model.*
import javax.inject.Inject

class MeaningMapper @Inject constructor() {

    fun mapItem(meaningResponse: MeaningResponse): MeaningWrapper {
        val translations = meaningResponse.meaningsWithSimilarTranslation
            .map(::toTranslationItem)
            .toMutableList()
        translations.add(0, TranslationItem(hasHeader = true))

        val examples = meaningResponse.examples
            .map(::toExampleItem)
            .toMutableList()
        examples.add(0, ExampleItem(hasHeader = true))

        return MeaningWrapper(
            transcription = toTranscriptionItem(meaningResponse),
            partOfSpeech = toPartOfSpeechItem(meaningResponse.partOfSpeechCode),
            definition = toDefinitionItem(meaningResponse.definition),
            translations = translations,
            examples = examples
        )
    }


    fun toTranscriptionItem(meaningResponse: MeaningResponse): TranscriptionItem =
        TranscriptionItem(
            translate = meaningResponse.translation.text,
            word = meaningResponse.text,
            transcription = meaningResponse.transcription
        )

    fun toPartOfSpeechItem(partOfSpeech: PartOfSpeech): PartOfSpeechItem =
        PartOfSpeechItem(partOfSpeech = partOfSpeech)

    fun toDefinitionItem(definition: Definition): DefinitionItem =
        DefinitionItem(definition = definition.text)

    fun toTranslationItem(meaning: MeaningsWithSimilarTranslation): TranslationItem =
        TranslationItem(
            id = meaning.meaningId,
            translation = meaning.translation.text,
            hasHeader = false,
            isClickable = true
        )

    fun toExampleItem(example: Example): ExampleItem =
        ExampleItem(
            example = example.text,
            hasHeader = false
        )
}

data class MeaningWrapper(
    val transcription: TranscriptionItem,
    val partOfSpeech: PartOfSpeechItem,
    val definition: DefinitionItem,
    val translations: List<TranslationItem>,
    val examples: List<ExampleItem>
)