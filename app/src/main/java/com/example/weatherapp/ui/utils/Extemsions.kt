package com.example.weatherapp.ui.utils

import android.view.View

/**
 * This function helps to toggle the visibility of a [View]. If the condition
 * is met, the [View] is made visible else it is hidden.
 */
inline fun <T : View> T.showIf(condition: (T) -> Boolean) {
    visibility = if (condition(this)) {
        View.VISIBLE
    } else {
        View.GONE
    }
}