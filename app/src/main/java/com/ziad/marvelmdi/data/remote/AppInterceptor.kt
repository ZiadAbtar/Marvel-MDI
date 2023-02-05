package com.ziad.marvelmdi.data.remote

import com.ziad.marvelmdi.utils.Constants.PRIVATE_KEY
import com.ziad.marvelmdi.utils.Constants.PUBLIC_KEY
import com.ziad.marvelmdi.utils.Helper
import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject


class AppInterceptor @Inject constructor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val timeStamp = System.currentTimeMillis().toString()
        val md5 = Helper.getMd5Hash(timeStamp, PRIVATE_KEY, PUBLIC_KEY)

        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter("ts", timeStamp)
            .addQueryParameter("hash", md5)
            .addQueryParameter("apikey", PUBLIC_KEY)
            .build()

        val newRequest = request.newBuilder().url(url).build()

        return chain.proceed(newRequest)
    }
}