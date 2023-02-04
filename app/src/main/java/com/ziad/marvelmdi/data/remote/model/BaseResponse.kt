package com.ziad.marvelmdi.data.remote.model

data class BaseResponse<T>(
    val code: Int,
    val data: T
)