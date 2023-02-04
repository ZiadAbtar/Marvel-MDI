package com.ziad.marvelmdi.data.remote

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AppInterceptor @Inject constructor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request()
            .newBuilder()

        val response = chain.proceed(builder.build())

        return response
    }
}