package com.example.simple_dictionary.search.presenter.model

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.simple_dictionary.R
import com.example.simple_dictionary.common.util.loadImage
import com.example.simple_dictionary.databinding.SearchResultItemBinding
import com.example.simple_dictionary.databinding.SearchResultItemBinding.inflate
import com.mikepenz.fastadapter.binding.AbstractBindingItem

data class SearchResultItem(
    val id: Int,
    val word: String,
    val meaning: String,
    val otherMeanings: List<String>,
    val imageUrl: String
) : AbstractBindingItem<SearchResultItemBinding>() {

    override val type: Int = R.id.fast_adapter_search_result_item

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?) =
        inflate(inflater, parent, false)


    override fun bindView(binding: SearchResultItemBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        with(binding) {
            wordText.text = word
            meaningText.text = meaning
            otherMeaningsText.text = otherMeanings.joinToString()
            previewImage.loadImage(imageUrl, R.drawable.ic_empty_image_placeholder_70, 8)
        }
    }
}