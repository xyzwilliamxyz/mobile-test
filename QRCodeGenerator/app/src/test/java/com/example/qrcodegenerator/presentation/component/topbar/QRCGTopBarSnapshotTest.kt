package com.example.qrcodegenerator.presentation.component.topbar

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class QRCGTopBarSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun `test QRCGTopBar`() {
        paparazzi.snapshot {
            QRCGTopBar_Preview()
        }
    }
}
