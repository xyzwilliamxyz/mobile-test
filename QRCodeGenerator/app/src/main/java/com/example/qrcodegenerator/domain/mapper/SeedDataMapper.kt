package com.example.qrcodegenerator.domain.mapper

import com.example.qrcodegenerator.data.remote.response.SeedResponse
import com.example.qrcodegenerator.domain.model.Seed
import javax.inject.Inject

class SeedDataMapper @Inject constructor() {
    fun mapToDomain(seedResponse: SeedResponse): Seed {
        return Seed(
            seed = seedResponse.seed ?: "",
            expiresAt = seedResponse.expiresAt ?: ""
        )
    }
}