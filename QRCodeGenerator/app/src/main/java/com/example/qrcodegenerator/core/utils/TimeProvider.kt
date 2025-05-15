package com.example.qrcodegenerator.core.utils

import java.util.Date

interface TimeProvider {
    fun now(): Date
}

class DefaultTimeProvider : TimeProvider {
    override fun now(): Date = Date()
}