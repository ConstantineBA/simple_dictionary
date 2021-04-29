package com.example.simple_dictionary.search_detail.data


import com.google.gson.annotations.SerializedName

data class MeaningsWithSimilarTranslation(
    @SerializedName("frequencyPercent")
    val frequencyPercent: String,
    @SerializedName("meaningId")
    val meaningId: Int,
    @SerializedName("partOfSpeechAbbreviation")
    val partOfSpeechAbbreviation: String,
    @SerializedName("translation")
    val translation: TranslationX
)