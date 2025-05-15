package com.example.qrcodegenerator.di

import android.content.Context
import com.example.qrcodegenerator.core.dispatcher.CoroutineDispatcherProvider
import com.example.qrcodegenerator.core.dispatcher.CoroutineDispatcherProviderImpl
import com.example.qrcodegenerator.core.network.executor.SourceExecutor
import com.example.qrcodegenerator.core.utils.DefaultTimeProvider
import com.example.qrcodegenerator.core.utils.TimeProvider
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
        @ApplicationContext appContext: Context,
        moshi: Moshi,
    ): SourceExecutor {
        return SourceExecutor(
            appContext = appContext,
            moshi = moshi,
        )
    }

    @Provides
    fun provideTimeProvider(): TimeProvider {
        return DefaultTimeProvider()
    }
}
