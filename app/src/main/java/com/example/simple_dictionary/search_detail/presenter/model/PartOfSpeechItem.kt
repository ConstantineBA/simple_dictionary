package com.example.simple_dictionary.search_detail.presenter.model

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.simple_dictionary.R
import com.example.simple_dictionary.common.util.getColorFromAttr
import com.example.simple_dictionary.databinding.SearchDetailPartOfSpeechItemBinding
import com.example.simple_dictionary.databinding.SearchDetailPartOfSpeechItemBinding.inflate
import com.example.simple_dictionary.search_detail.presenter.model.PartOfSpeech.*
import com.mikepenz.fastadapter.binding.AbstractBindingItem

data class PartOfSpeechItem(
    val partOfSpeech: PartOfSpeech
) : AbstractBindingItem<SearchDetailPartOfSpeechItemBinding>() {
    override val type: Int = R.id.fast_adapter_search_details_part_of_speech

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?) =
        inflate(inflater, parent, false)

    override fun bindView(binding: SearchDetailPartOfSpeechItemBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        val color = ColorStateList.valueOf(binding.root.getColorFromAttr(R.attr.colorPrimary))
        when (partOfSpeech) {
            ADJECTIVE -> binding.adjective.backgroundTintList = color
            NOUN -> binding.noun.backgroundTintList = color
            VERB -> binding.verb.backgroundTintList = color
            UNKNOWN -> binding.root.isVisible = false
        }
    }
}
