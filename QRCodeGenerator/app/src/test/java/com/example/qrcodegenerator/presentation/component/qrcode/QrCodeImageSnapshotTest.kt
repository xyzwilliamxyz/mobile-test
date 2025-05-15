package com.example.qrcodegenerator.presentation.component.qrcode

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class QRCodeImageSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun `test QRCodeImage`() {
        paparazzi.snapshot {
            QRCodeImage_Preview()
        }
    }
}
