package com.example.simple_dictionary.search_detail.presenter.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.simple_dictionary.R
import com.example.simple_dictionary.databinding.CommonClickArrowItemBinding
import com.example.simple_dictionary.databinding.CommonClickArrowItemBinding.inflate
import com.mikepenz.fastadapter.binding.AbstractBindingItem

data class TranslationItem(
    val id: Int,
    val translation: String,
    val hasHeader: Boolean,
    val isClickable: Boolean = false
) : AbstractBindingItem<CommonClickArrowItemBinding>() {
    override val type: Int = R.id.fast_adapter_search_details_transcription

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?) =
        inflate(inflater, parent, false)

    override fun bindView(binding: CommonClickArrowItemBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        with(binding) {
            headerText.text = root.context.getText(R.string.search_detail_other_label)
            headerText.isVisible = hasHeader
            descriptionText.text = translation
            actionImage.isVisible = isClickable
        }
    }
}
