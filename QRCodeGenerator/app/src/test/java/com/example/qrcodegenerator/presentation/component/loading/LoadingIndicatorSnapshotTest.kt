package com.example.qrcodegenerator.presentation.component.loading

import app.cash.paparazzi.Paparazzi
import com.example.qrcodegenerator.core.theme.QRCodeGeneratorTheme
import org.junit.Rule
import org.junit.Test

class LoadingIndicatorSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun `test LoadingIndicator`() {
        paparazzi.snapshot {
            QRCodeGeneratorTheme {
                LoadingIndicator()
            }
        }
    }
}
