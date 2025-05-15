package com.example.qrcodegenerator.presentation.component.fabmenu

import app.cash.paparazzi.Paparazzi
import com.example.qrcodegenerator.core.theme.QRCodeGeneratorTheme
import com.example.qrcodegenerator.presentation.home.HomeFabOption
import org.junit.Rule
import org.junit.Test

class SimpleFabMenuSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun `test SimpleFabMenu`() {
        paparazzi.snapshot {
            QRCodeGeneratorTheme {
                SimpleFabMenu(
                    options = listOf(
                        HomeFabOption.QRCodeGeneration,
                        HomeFabOption.QRCodeScan,
                    ),
                    onClick = {},
                )
            }
        }
    }
}
