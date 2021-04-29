package com.example.simple_dictionary.core.mappers

import com.example.simple_dictionary.search_detail.data.Definition
import com.example.simple_dictionary.search_detail.data.Example
import com.example.simple_dictionary.search_detail.data.MeaningResponse
import com.example.simple_dictionary.search_detail.data.MeaningsWithSimilarTranslation
import com.example.simple_dictionary.search_detail.presenter.model.*
import javax.inject.Inject

class MeaningMapper @Inject constructor() {

    fun mapItem(meaningResponse: MeaningResponse): MeaningWrapper =
        MeaningWrapper(
            transcription = toTranscriptionItem(meaningResponse),
            partOfSpeech = toPartOfSpeechItem(meaningResponse.partOfSpeechCode),
            definition = toDefinitionItem(meaningResponse.definition),
            translations = meaningResponse.meaningsWithSimilarTranslation
                .mapIndexed { index, meanint ->
                    toTranslationItem(meanint, index == 0)
                },
            examples = meaningResponse.examples
                .mapIndexed { index, example ->
                    toExampleItem(example, index == 0)
                }
        )

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

    fun toTranslationItem(
        meaning: MeaningsWithSimilarTranslation,
        hasHeader: Boolean
    ): TranslationItem =
        TranslationItem(
            id = meaning.meaningId,
            translation = meaning.translation.text,
            hasHeader = hasHeader,
            isClickable = true
        )

    fun toExampleItem(example: Example, hasHeader: Boolean): ExampleItem =
        ExampleItem(
            example = example.text,
            hasHeader = hasHeader
        )
}

data class MeaningWrapper(
    val transcription: TranscriptionItem,
    val partOfSpeech: PartOfSpeechItem,
    val definition: DefinitionItem,
    val translations: List<TranslationItem>,
    val examples: List<ExampleItem>
)