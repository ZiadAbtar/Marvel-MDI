package com.ziad.marvelmdi.data.remote.model

data class ComicsResponse(
    val results: List<Comic>
) : BaseDataResponse()