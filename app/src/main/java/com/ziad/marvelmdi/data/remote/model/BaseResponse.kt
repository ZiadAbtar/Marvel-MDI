package com.ziad.marvelmdi.data.remote.model

data class BaseResponse<T>(
    val code: Int,
    val message: String?,
    val data: T
)