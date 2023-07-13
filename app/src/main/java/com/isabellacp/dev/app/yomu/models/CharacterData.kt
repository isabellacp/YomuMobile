package com.isabellacp.dev.app.yomu.models

import com.google.gson.annotations.SerializedName

data class CharacterData(
    val id: String?,
    val type: String?,
    @SerializedName("attributes")
    val attributes: CharacterAttributes,
)
