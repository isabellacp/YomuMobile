package com.isabellacp.dev.app.yomu.models

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("data")
    val data: List<CharacterData>,
)
