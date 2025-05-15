package com.example.qrcodegenerator.presentation.home

import app.cash.paparazzi.Paparazzi
import com.example.qrcodegenerator.core.theme.QRCodeGeneratorTheme
import org.junit.Rule
import org.junit.Test

class HomeScreenSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun `test HomeScreen initial state`() {
        paparazzi.snapshot {
            QRCodeGeneratorTheme {
                HomeScreen_Preview()
            }
        }
    }

    @Test
    fun `test HomeScreen with fab menu expanded`() {
        paparazzi.snapshot {
            QRCodeGeneratorTheme {
                HomeScreen_FabMenuExpanded_Preview()
            }
        }
    }
}
