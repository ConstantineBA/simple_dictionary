package com.example.simple_dictionary.search_detail.presenter.model

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.simple_dictionary.R
import com.example.simple_dictionary.databinding.SearchDetailPartOfSpeechItemBinding
import com.example.simple_dictionary.databinding.SearchDetailPartOfSpeechItemBinding.inflate
import com.mikepenz.fastadapter.binding.AbstractBindingItem

data class PartOfSpeechItem(
    val partOfSpeech: PartOfSpeech
) : AbstractBindingItem<SearchDetailPartOfSpeechItemBinding>() {
    override val type: Int = R.id.fast_adapter_search_details_part_of_speech

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?) =
        inflate(inflater, parent, false)

    override fun bindView(binding: SearchDetailPartOfSpeechItemBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
    }
}
