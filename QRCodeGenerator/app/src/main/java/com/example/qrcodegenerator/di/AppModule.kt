package com.example.qrcodegenerator.di

import android.app.Application
import com.example.qrcodegenerator.core.dispatcher.CoroutineDispatcherProvider
import com.example.qrcodegenerator.core.dispatcher.CoroutineDispatcherProviderImpl
import com.example.qrcodegenerator.core.network.executor.SourceExecutor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcherProvider {
        return CoroutineDispatcherProviderImpl()
    }

    @Provides
    fun provideSourceExecutor(
        @ApplicationContext appContext: Application,
        moshi: Moshi,
    ): SourceExecutor {
        return SourceExecutor(
            appContext = appContext,
            moshi = moshi
        )
    }
}