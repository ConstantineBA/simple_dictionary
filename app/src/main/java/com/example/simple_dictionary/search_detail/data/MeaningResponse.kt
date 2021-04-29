package com.example.simple_dictionary.search_detail.data


import com.example.simple_dictionary.search_detail.presenter.model.PartOfSpeech
import com.google.gson.annotations.SerializedName

data class MeaningResponse(
    @SerializedName("alternativeTranslations")
    val alternativeTranslations: List<AlternativeTranslation>,
    @SerializedName("definition")
    val definition: Definition,
    @SerializedName("difficultyLevel")
    val difficultyLevel: Int,
    @SerializedName("examples")
    val examples: List<Example>,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("meaningsWithSimilarTranslation")
    val meaningsWithSimilarTranslation: List<MeaningsWithSimilarTranslation>,
    @SerializedName("mnemonics")
    val mnemonics: String,
    @SerializedName("partOfSpeechCode")
    val partOfSpeechCode: PartOfSpeech,
    @SerializedName("prefix")
    val prefix: String,
    @SerializedName("properties")
    val properties: Properties,
    @SerializedName("soundUrl")
    val soundUrl: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("transcription")
    val transcription: String,
    @SerializedName("translation")
    val translation: TranslationXX,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("wordId")
    val wordId: Int
)