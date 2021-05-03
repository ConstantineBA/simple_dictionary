package com.example.simple_dictionary

import com.example.simple_dictionary.core.gson.GsonFactory
import com.example.simple_dictionary.search_detail.presenter.model.PartOfSpeech
import org.junit.Assert.assertEquals
import org.junit.Test

class PartOfSpeechDeserializerTest {

    private val gson = GsonFactory.getGson()

    @Test
    fun getUnknownPartOfSpeech() {
        val inputEnum = "prn"
        val result = gson.fromJson(gson.toJson(inputEnum), PartOfSpeech::class.java)
        assertEquals(PartOfSpeech.UNKNOWN, result)
    }

    @Test
    fun getNoun() {
        val inputEnum = "n"
        val result = gson.fromJson(gson.toJson(inputEnum), PartOfSpeech::class.java)
        assertEquals(PartOfSpeech.NOUN, result)
    }

    @Test
    fun getVerb() {
        val inputEnum = "v"
        val result = gson.fromJson(gson.toJson(inputEnum), PartOfSpeech::class.java)
        assertEquals(PartOfSpeech.VERB, result)
    }

    @Test
    fun getAdjective() {
        val inputEnum = "j"
        val result = gson.fromJson(gson.toJson(inputEnum), PartOfSpeech::class.java)
        assertEquals(PartOfSpeech.ADJECTIVE, result)
    }
}