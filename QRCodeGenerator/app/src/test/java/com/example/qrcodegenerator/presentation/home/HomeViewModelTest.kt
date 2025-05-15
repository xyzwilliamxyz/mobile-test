package com.example.qrcodegenerator.presentation.home

import app.cash.turbine.test
import com.example.qrcodegenerator.testutils.TestCoroutineDispatcherProviderImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val testDispatcher = StandardTestDispatcher()
    private val testDispatcherProvider = TestCoroutineDispatcherProviderImpl(testDispatcher)

    @Before
    fun setUp() {
        viewModel = HomeViewModel(testDispatcherProvider)
        viewModel.initialize()
    }

    @Test
    fun `initialize sets fabOptions correctly`() = runTest(testDispatcher) {
        val expectedOptions = listOf(
            HomeFabOption.QRCodeGeneration,
            HomeFabOption.QRCodeScan,
        )
        assertEquals(expectedOptions, viewModel.state.value.fabOptions)
    }

    @Test
    fun `onFabActionClick emits QRCodeGeneration navigation`() = runTest(testDispatcher) {
        viewModel.navigation.test {
            viewModel.actions.onFacActionClick(HomeFabOption.QRCodeGeneration)
            assertEquals(HomeNavigation.QRCodeGeneration, awaitItem())
        }
    }

    @Test
    fun `onFabActionClick emits QRCodeScan navigation`() = runTest(testDispatcher) {
        viewModel.navigation.test {
            viewModel.actions.onFacActionClick(HomeFabOption.QRCodeScan)
            assertEquals(HomeNavigation.QRCodeScan, awaitItem())
        }
    }
}
