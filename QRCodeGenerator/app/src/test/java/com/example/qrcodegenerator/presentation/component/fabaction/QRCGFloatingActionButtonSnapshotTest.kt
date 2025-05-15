package com.example.qrcodegenerator.presentation.component.fabaction

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class QRCGFloatingActionButtonSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun `test QRCGFloatingActionButton`() {
        paparazzi.snapshot {
            QRCGFloatingActionButton_Preview()
        }
    }
}
