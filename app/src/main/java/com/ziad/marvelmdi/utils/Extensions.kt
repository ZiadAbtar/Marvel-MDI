package com.ziad.marvelmdi.utils

import com.ziad.marvelmdi.data.remote.model.Thumbnail

fun Thumbnail.getUsableUrl(): String {
    return path.replace("http","https").plus(".$extension")
}