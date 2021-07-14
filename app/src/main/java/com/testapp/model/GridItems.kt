package com.testapp.model

import android.graphics.Color

data class GridItems(
    var color: Int = Color.WHITE,
) {

    val isClickable: Boolean = !(color == Color.WHITE || color == Color.BLUE)

}
