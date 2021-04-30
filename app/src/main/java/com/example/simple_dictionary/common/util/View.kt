package com.example.simple_dictionary.common.util

import android.R
import android.content.res.Resources.Theme
import android.util.TypedValue
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt


fun View.getColorFromAttr(@AttrRes attrRes: Int): Int {
    val typedValue = TypedValue()
    val theme = context.theme
    theme.resolveAttribute(attrRes, typedValue, true)
    return typedValue.data
}