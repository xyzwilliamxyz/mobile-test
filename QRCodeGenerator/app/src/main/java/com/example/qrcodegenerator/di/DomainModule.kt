package com.example.qrcodegenerator.di

import com.example.qrcodegenerator.data.repository.QRCodeRepository
import com.example.qrcodegenerator.domain.interactor.GenerateSeedInteractor
import com.example.qrcodegenerator.domain.interactor.GenerateSeedInteractorImpl
import com.example.qrcodegenerator.domain.mapper.SeedDataMapper
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    fun providesGenerateSeedInteractor(
        qrCodeRepository: QRCodeRepository,
        seedDataMapper: SeedDataMapper
    ): GenerateSeedInteractor {
        return GenerateSeedInteractorImpl(
            qrCodeRepository = qrCodeRepository,
            seedDataMapper = seedDataMapper
        )
    }
}