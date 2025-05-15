package com.example.qrcodegenerator.presentation.component.fabmenu

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class SimpleFabMenuSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun `test SimpleFabMenu`() {
        paparazzi.snapshot {
            SimpleFabMenu_Preview()
        }
    }
}
