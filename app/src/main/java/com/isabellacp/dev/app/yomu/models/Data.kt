package com.isabellacp.dev.app.yomu.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Data(
    @PrimaryKey val id: String,
    val type: String?,
    var is_favorited: Boolean?,
    val attributes: Attributes
) : Serializable
