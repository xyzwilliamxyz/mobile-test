package com.example.qrcodegenerator.presentation.qrcodegeneration

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class QRCodeGenerationScreenSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun `test QRCodeGenerationScreen`() {
        paparazzi.snapshot {
            QRCodeGenerationScreen_Preview()
        }
    }
}
