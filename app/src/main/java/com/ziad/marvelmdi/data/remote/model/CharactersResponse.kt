package com.ziad.marvelmdi.data.remote.model

data class CharactersResponse(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character>
)