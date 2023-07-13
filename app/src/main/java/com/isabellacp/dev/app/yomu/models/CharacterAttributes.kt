package com.isabellacp.dev.app.yomu.models

data class CharacterAttributes(
    val slug: String?,
    val canonicalname: String?,
    //val names: Titles?,
    val description: String?,
    val image: PosterImage?, // will be changed if it doesn't work
)
