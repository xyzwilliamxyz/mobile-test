package com.example.qrcodegenerator.data.remote.response

import com.squareup.moshi.Json

data class SeedResponse(
    @Json(name = "expires_at")
    val expiresAt: String? = null,

    @Json(name = "seed")
    val seed: String? = null,
)
