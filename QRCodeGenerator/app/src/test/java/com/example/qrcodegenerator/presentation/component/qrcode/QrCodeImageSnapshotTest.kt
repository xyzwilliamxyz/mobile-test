package com.example.qrcodegenerator.presentation.component.qrcode

import app.cash.paparazzi.Paparazzi
import com.example.qrcodegenerator.core.theme.QRCodeGeneratorTheme
import org.junit.Rule
import org.junit.Test

class QRCodeImageSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun `test QRCodeImage`() {
        paparazzi.snapshot {
            QRCodeGeneratorTheme {
                QrCodeImage(
                    seed = "49e5982edac923b3b4f29ff9da179d8f",
                )
            }
        }
    }
}
