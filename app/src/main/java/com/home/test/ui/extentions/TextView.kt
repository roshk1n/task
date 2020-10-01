package com.home.test.ui.extentions

import android.widget.TextView

fun TextView.text(): String {
    return this.text.toString()
}