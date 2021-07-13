package com.ojanbelajar.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location (
    val name: String
): Parcelable