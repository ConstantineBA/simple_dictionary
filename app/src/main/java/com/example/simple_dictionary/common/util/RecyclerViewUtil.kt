package com.example.simple_dictionary.common.util

import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter

@Suppress("UNCHECKED_CAST")
val RecyclerView.genericFastItemAdapter
    get() = this.adapter as GenericFastItemAdapter