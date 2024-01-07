package com.example.monthofwellness.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Wellness(
    val index:Int,
    @StringRes val titleStringRes:Int,
    @StringRes val bodyStringRes:Int,
    @DrawableRes val imageRes:Int
)
