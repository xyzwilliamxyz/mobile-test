package com.example.qrcodegenerator.presentation.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import app.cash.paparazzi.Paparazzi
import com.example.qrcodegenerator.core.theme.QRCodeGeneratorTheme
import org.junit.Rule
import org.junit.Test

class QRCGTopBarSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun `test QRCGTopBar`() {
        paparazzi.snapshot {
            QRCodeGeneratorTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                ) {
                    QRCGTopBar(
                        "Title",
                        onBackClick = {},
                    )
                }
            }
        }
    }
}
