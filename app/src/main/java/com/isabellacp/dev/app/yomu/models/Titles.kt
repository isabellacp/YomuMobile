package com.isabellacp.dev.app.yomu.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Titles(
    @SerializedName("ja_jp")
    val jaJp: String?,
    @SerializedName("en_us")
    val engUs: String?,
    @SerializedName("en_jp")
    val enJp: String?
) : Serializable
