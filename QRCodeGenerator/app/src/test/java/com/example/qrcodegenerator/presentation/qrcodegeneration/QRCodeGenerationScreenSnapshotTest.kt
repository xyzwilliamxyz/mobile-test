package com.example.qrcodegenerator.presentation.qrcodegeneration

import app.cash.paparazzi.Paparazzi
import com.example.qrcodegenerator.core.theme.QRCodeGeneratorTheme
import org.junit.Rule
import org.junit.Test

class QRCodeGenerationScreenSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun `test QRCodeGenerationScreen`() {
        paparazzi.snapshot {
            QRCodeGeneratorTheme {
                QRCodeGenerationScreenInternal(
                    state = QRCodeGenerationState(
                        seed = "03274be7c8d2ef35b0026d88f257f300",
                        timeRemaining = 15,
                    ),
                    onBackClick = {},
                )
            }
        }
    }
}
