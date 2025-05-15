package com.example.qrcodegenerator.testutils

import com.example.qrcodegenerator.core.dispatcher.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestCoroutineDispatcherProviderImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Unconfined,
) : CoroutineDispatcherProvider {
    override fun io() = dispatcher

    override fun default() = dispatcher

    override fun main() = dispatcher
}
