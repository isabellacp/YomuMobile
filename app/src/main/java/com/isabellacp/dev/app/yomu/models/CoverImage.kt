package com.isabellacp.dev.app.yomu.models

import java.io.Serializable

data class CoverImage(
    val tiny: String?,
    val large: String?,
    val small: String?,
    val original: String?,
) : Serializable
