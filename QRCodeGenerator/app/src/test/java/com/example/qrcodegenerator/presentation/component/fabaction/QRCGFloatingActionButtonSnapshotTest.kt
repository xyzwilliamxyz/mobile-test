package com.example.qrcodegenerator.presentation.component.fabaction

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import app.cash.paparazzi.Paparazzi
import com.example.qrcodegenerator.core.theme.QRCodeGeneratorTheme
import org.junit.Rule
import org.junit.Test

class QRCGFloatingActionButtonSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun `test QRCGFloatingActionButton`() {
        paparazzi.snapshot {
            QRCodeGeneratorTheme {
                QRCGFloatingActionButton(
                    icon = Icons.Default.Add,
                    onClick = {},
                )
            }
        }
    }
}
