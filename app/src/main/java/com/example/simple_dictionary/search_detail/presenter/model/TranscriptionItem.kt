package com.example.simple_dictionary.search_detail.presenter.model

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.simple_dictionary.R
import com.example.simple_dictionary.databinding.SearchDetailTranscrtiptionItemBinding
import com.example.simple_dictionary.databinding.SearchDetailTranscrtiptionItemBinding.inflate
import com.mikepenz.fastadapter.binding.AbstractBindingItem

data class TranscriptionItem(
    val translate: String,
    val word: String,
    val transcription: String
) : AbstractBindingItem<SearchDetailTranscrtiptionItemBinding>() {
    override val type: Int = R.id.fast_adapter_search_details_translate

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?) =
        inflate(inflater, parent, false)

    override fun bindView(binding: SearchDetailTranscrtiptionItemBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        with(binding) {
            transcriptionText.text = transcription
            translateText.text = translate
            wordText.text = word
        }
    }
}
