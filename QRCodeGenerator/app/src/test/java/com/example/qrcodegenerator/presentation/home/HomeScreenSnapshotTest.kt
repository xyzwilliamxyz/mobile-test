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
                HomeScreenInternal(
                    state = HomeState(),
                    actions = HomeActions(),
                )
            }
        }
    }

    @Test
    fun `test HomeScreen with fab menu expanded`() {
        paparazzi.snapshot {
            QRCodeGeneratorTheme {
                HomeScreenInternal(
                    state = HomeState(
                        fabOptions = listOf(
                            HomeFabOption.QRCodeGeneration,
                            HomeFabOption.QRCodeScan,
                        ),
                    ),
                    actions = HomeActions(),
                )
            }
        }
    }
}
