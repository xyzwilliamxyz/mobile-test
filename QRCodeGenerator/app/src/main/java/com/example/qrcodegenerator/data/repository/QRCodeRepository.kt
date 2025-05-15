package com.example.qrcodegenerator.data.repository

import com.example.qrcodegenerator.core.network.executor.SourceResult
import com.example.qrcodegenerator.data.remote.response.SeedResponse

interface QRCodeRepository {
    suspend fun getSeed(): SourceResult<SeedResponse>
}
