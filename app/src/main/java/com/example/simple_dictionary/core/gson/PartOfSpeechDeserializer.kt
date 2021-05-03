package com.example.simple_dictionary.core.gson

import com.example.simple_dictionary.search_detail.presenter.model.PartOfSpeech
import com.example.simple_dictionary.search_detail.presenter.model.PartOfSpeech.UNKNOWN
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

object PartOfSpeechDeserializer : JsonDeserializer<PartOfSpeech> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PartOfSpeech {
        return  Gson().fromJson(json, PartOfSpeech::class.java) ?: UNKNOWN
    }
}