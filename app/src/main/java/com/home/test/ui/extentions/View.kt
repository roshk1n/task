package com.home.test.ui.extentions

import android.opengl.Visibility
import android.view.View

fun View.setVisibility(boolean: Boolean) {
    visibility = if (boolean) {
        View.VISIBLE
    } else {
        View.GONE
    }
}