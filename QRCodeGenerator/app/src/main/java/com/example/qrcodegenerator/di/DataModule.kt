package com.example.qrcodegenerator.di

import com.example.qrcodegenerator.core.network.executor.SourceExecutor
import com.example.qrcodegenerator.data.remote.QRCodeRemoteRepositoryImpl
import com.example.qrcodegenerator.data.remote.api.QRCodeApi
import com.example.qrcodegenerator.data.repository.QRCodeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideQRCodeRemoteRepository(
        qrCodeApi: QRCodeApi,
        sourceExecutor: SourceExecutor,
    ): QRCodeRepository {
        return QRCodeRemoteRepositoryImpl(
            qrCodeApi = qrCodeApi,
            sourceExecutor = sourceExecutor,
        )
    }
}
