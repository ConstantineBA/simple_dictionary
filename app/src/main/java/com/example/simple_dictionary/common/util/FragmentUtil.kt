package com.example.simple_dictionary.common.util

import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.getDrawableByAttr(
    @AttrRes drawableAttr: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Drawable? {
    val isFound = requireContext().theme.resolveAttribute(drawableAttr, typedValue, resolveRefs)
    require(isFound)
    return ContextCompat.getDrawable(requireContext(), typedValue.resourceId)
}