package com.mkrlabs.dashboard.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class FeatureItem (
    val featureCode : String ? = null,
    val featureName : String ? = null,
    val iconUrl : String ? = null,
    val drawable : Int ? = null
) : Parcelable