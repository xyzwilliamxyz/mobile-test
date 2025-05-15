package com.example.qrcodegenerator.presentation.component.fabaction

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.qrcodegenerator.core.theme.QRCodeGeneratorTheme

@Composable
fun QRCGFloatingActionButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FloatingActionButton(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        onClick = onClick,
    ) {
        Icon(icon, contentDescription = icon.name)
    }
}

@Preview
@Composable
private fun QRCGFloatingActionButton_Preview() {
    QRCodeGeneratorTheme {
        QRCGFloatingActionButton(
            icon = Icons.Default.Add,
            onClick = {},
        )
    }
}
