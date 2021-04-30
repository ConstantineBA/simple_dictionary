package com.example.simple_dictionary.search_detail.presenter.model

import com.google.gson.annotations.SerializedName

enum class PartOfSpeech{
    @SerializedName("j")
    ADJECTIVE,
    @SerializedName("n")
    NOUN,
    @SerializedName("v")
    VERB,

    UNKNOWN
}
