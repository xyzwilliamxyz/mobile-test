package com.example.qrcodegenerator.presentation.component.loading

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class LoadingIndicatorSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun `test LoadingIndicator`() {
        paparazzi.snapshot {
            LoadingIndicator_Preview()
        }
    }
}
