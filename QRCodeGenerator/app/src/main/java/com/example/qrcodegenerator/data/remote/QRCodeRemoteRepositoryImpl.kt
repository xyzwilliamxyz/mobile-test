package com.example.qrcodegenerator.data.remote

import com.example.qrcodegenerator.core.network.executor.SourceExecutor
import com.example.qrcodegenerator.core.network.executor.SourceResult
import com.example.qrcodegenerator.data.remote.api.QRCodeApi
import com.example.qrcodegenerator.data.remote.response.SeedResponse
import com.example.qrcodegenerator.data.repository.QRCodeRepository

class QRCodeRemoteRepositoryImpl(
    private val sourceExecutor: SourceExecutor,
    private val qrCodeApi: QRCodeApi
) : QRCodeRepository {

    override suspend fun getSeed(): SourceResult<SeedResponse> {
        return sourceExecutor.execute {
            qrCodeApi.getSeed()
        }
    }
}