package com.example.qrcodegenerator.domain.mapper

import com.example.qrcodegenerator.core.utils.TimeProvider
import com.example.qrcodegenerator.core.utils.toDate
import com.example.qrcodegenerator.core.utils.toSeconds
import com.example.qrcodegenerator.data.remote.response.SeedResponse
import com.example.qrcodegenerator.domain.model.Seed
import java.util.Date
import javax.inject.Inject

class SeedDataMapper @Inject constructor(
    private val timeProvider: TimeProvider
) {

    fun mapToDomain(seedResponse: SeedResponse): Seed {
        val expiresAt = seedResponse.expiresAt.orEmpty()
        val expiresAtInSeconds = expiresAt.toDate()?.toSeconds() ?: 0L
        val secondsRemaining = expiresAtInSeconds - timeProvider.now().toSeconds()

        return Seed(
            seed = seedResponse.seed.orEmpty(),
            expiresAt = expiresAt,
            secondsRemaining = secondsRemaining.toInt(),
        )
    }
}
