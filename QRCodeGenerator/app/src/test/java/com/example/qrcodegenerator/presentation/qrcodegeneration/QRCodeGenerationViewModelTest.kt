package com.example.qrcodegenerator.presentation.qrcodegeneration

import app.cash.turbine.test
import com.example.qrcodegenerator.domain.interactor.GenerateSeedInteractor
import com.example.qrcodegenerator.testutils.MockFactory
import com.example.qrcodegenerator.testutils.TestCoroutineDispatcherProviderImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class QRCodeGenerationViewModelTest {

    private lateinit var viewModel: QRCodeGenerationViewModel
    private val generateSeedInteractor: GenerateSeedInteractor = mockk()
    private val testDispatcher = StandardTestDispatcher()
    private val testDispatcherProvider = TestCoroutineDispatcherProviderImpl(testDispatcher)

    @Before
    fun setup() {
        viewModel = QRCodeGenerationViewModel(generateSeedInteractor, testDispatcherProvider)
        viewModel.setTimer(false)
    }

    @Test
    fun `generateSeed updates state with loading and success`() = runTest(testDispatcher) {
        val seedResult = MockFactory.getSeed()
        coEvery { generateSeedInteractor.invoke() } returns seedResult
        viewModel.initialize()

        viewModel.state.test {
            assertEquals(QRCodeGenerationState(isLoading = false, seed = "", timeRemaining = 0), awaitItem())
            assertEquals(QRCodeGenerationState(isLoading = true, seed = "", timeRemaining = 0), awaitItem())
            assertEquals(QRCodeGenerationState(isLoading = false, seed = "test-seed", timeRemaining = 10), awaitItem())
        }
    }

    @Test
    fun `generateSeed updates state with failure`() = runTest(testDispatcher) {
        coEvery { generateSeedInteractor.invoke() } throws Exception("Error")
        viewModel.initialize()

        viewModel.state.test {
            assertEquals(QRCodeGenerationState(isLoading = false, seed = "", timeRemaining = 0), awaitItem())
            assertEquals(QRCodeGenerationState(isLoading = true, seed = "", timeRemaining = 0), awaitItem())
            assertEquals(QRCodeGenerationState(isLoading = false, seed = "", timeRemaining = 0), awaitItem())
        }
    }

    @Test
    fun `startTimer decrements timeRemaining and regenerates seed`() = runTest(testDispatcher) {
        val seedResult = MockFactory.getSeed(timeRemaining = 1)
        coEvery { generateSeedInteractor.invoke() } returns seedResult
        viewModel.setTimer(true)
        viewModel.initialize()

        viewModel.state.test {
            assertEquals(QRCodeGenerationState(isLoading = false, seed = "", timeRemaining = 0), awaitItem())
            assertEquals(QRCodeGenerationState(isLoading = true, seed = "", timeRemaining = 0), awaitItem())
            assertEquals(QRCodeGenerationState(isLoading = false, seed = "test-seed", timeRemaining = 1), awaitItem())
            viewModel.setTimer(false)
            advanceUntilIdle()
            assertEquals(QRCodeGenerationState(isLoading = false, seed = "test-seed", timeRemaining = 0), awaitItem())
            assertEquals(QRCodeGenerationState(isLoading = true, seed = "", timeRemaining = 0), awaitItem())
            assertEquals(QRCodeGenerationState(isLoading = false, seed = "test-seed", timeRemaining = 1), awaitItem())
        }
    }
}
