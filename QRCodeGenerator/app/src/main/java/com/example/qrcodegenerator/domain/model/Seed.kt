package com.example.qrcodegenerator.domain.model

data class Seed(
    val seed: String,
    val expiresAt: String,
    val secondsRemaining: Int,
)