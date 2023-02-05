package com.ziad.marvelmdi.utils

import java.math.BigInteger
import java.security.MessageDigest

object Helper {

    fun getMd5Hash(timeStampString: String, privateKey: String, publicKey: String): String {
        val toHash = timeStampString.plus(privateKey).plus(publicKey)
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toHash.toByteArray())).toString(16).padStart(32, '0')
    }
}