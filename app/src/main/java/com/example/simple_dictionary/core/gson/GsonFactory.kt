package com.example.simple_dictionary.core.gson

import com.example.simple_dictionary.search_detail.presenter.model.PartOfSpeech
import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonFactory {

    fun getGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(PartOfSpeech::class.java, PartOfSpeechDeserializer)
            .create()
    }
}