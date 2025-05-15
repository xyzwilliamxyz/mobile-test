package com.example.qrcodegenerator.core.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

private const val SECOND_MILLISECONDS = 1000L

fun String.toDate(): Date? {
    return runCatching {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ROOT)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        sdf.parse(this)
    }.getOrNull()
}

fun Date.toSeconds(): Long {
    return this.time / SECOND_MILLISECONDS
}