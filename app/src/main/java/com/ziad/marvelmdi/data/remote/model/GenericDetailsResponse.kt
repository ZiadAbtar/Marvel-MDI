package com.ziad.marvelmdi.data.remote.model

data class GenericDetailsResponse(
    val results: List<GenericDetail>
) : BaseDataResponse()