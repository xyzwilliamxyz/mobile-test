package com.example.qrcodegenerator.presentation.home

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel()
        viewModel.initialize()
    }

    @Test
    fun `initialize sets fabOptions correctly`() = runTest {
        val expectedOptions = listOf(
            HomeFabOption.QRCodeGeneration,
            HomeFabOption.QRCodeScan,
        )
        assertEquals(expectedOptions, viewModel.state.value.fabOptions)
    }

    @Test
    fun `onFabActionClick emits QRCodeGeneration navigation`() = runTest {
        viewModel.navigation.test {
            viewModel.actions.onFacActionClick(HomeFabOption.QRCodeGeneration)
            assertEquals(HomeNavigation.QRCodeGeneration, awaitItem())
        }
    }

    @Test
    fun `onFabActionClick emits QRCodeScan navigation`() = runTest {
        viewModel.navigation.test {
            viewModel.actions.onFacActionClick(HomeFabOption.QRCodeScan)
            assertEquals(HomeNavigation.QRCodeScan, awaitItem())
        }
    }
}
