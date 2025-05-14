package com.example.qrcodegenerator.domain.interactor

import com.example.qrcodegenerator.data.repository.QRCodeRepository
import com.example.qrcodegenerator.domain.mapper.SeedDataMapper
import com.example.qrcodegenerator.domain.model.Seed

interface GenerateSeedInteractor {
    suspend operator fun invoke() : Seed
}

class GenerateSeedInteractorImpl(
    private val qrCodeRepository: QRCodeRepository,
    private val seedDataMapper: SeedDataMapper
) : GenerateSeedInteractor {

    override suspend fun invoke(): Seed {
        val seedResponse = qrCodeRepository.getSeed().getBodyOrThrow()
        return seedDataMapper.mapToDomain(seedResponse)
    }
}