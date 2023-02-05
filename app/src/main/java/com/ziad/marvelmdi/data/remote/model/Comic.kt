package com.ziad.marvelmdi.data.remote.model

data class Comic(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: Thumbnail,
    val resourceURI: String
) : BaseDataResponse()
