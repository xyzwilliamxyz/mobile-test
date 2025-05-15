package com.example.qrcodegenerator.presentation.qrcodescan

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class QRCodeScanViewModelTest {
    private val viewModel = QRCodeScanViewModel()

    @Test
    fun `onQRCodeScanned should update state with scanned code`() = runTest {
        val testQRCode = "test-qr-123"
        viewModel.actions.onQRCodeScanned(testQRCode)

        val currentState = viewModel.state.value
        assertEquals(testQRCode, currentState.qrCodeRaw)
    }
}
