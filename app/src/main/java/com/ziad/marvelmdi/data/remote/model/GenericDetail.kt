package com.ziad.marvelmdi.data.remote.model

data class GenericDetail(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: Thumbnail,
    val resourceURI: String
) : BaseDataResponse()
