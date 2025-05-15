package com.example.qrcodegenerator.presentation.component.fabmenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.qrcodegenerator.core.theme.QRCodeGeneratorTheme
import com.example.qrcodegenerator.presentation.component.fabaction.QRCGFloatingActionButton
import com.example.qrcodegenerator.presentation.home.HomeFabOption

@Composable
fun SimpleFabMenu(options: List<FabMenuOption>, onClick: (FabMenuOption) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.End
        ) {
            val actonIcon = if (expanded) Icons.Default.Close else Icons.Default.Add
            if (expanded) {
                options.forEach { option ->
                    FabMenuItem(
                        option = option,
                        onClick = { onClick(option) }
                    )
                }
            }

            QRCGFloatingActionButton(
                icon = actonIcon,
                onClick = { expanded = !expanded }
            )
        }
    }
}

@Composable
private fun FabMenuItem(option: FabMenuOption, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(text = stringResource(option.description))
        Spacer(modifier = Modifier.width(8.dp))
        Icon(imageVector = option.icon, contentDescription = stringResource(option.description))
    }
}

@Preview
@Composable
fun SimpleFabMenu_Preview() {
    QRCodeGeneratorTheme {
        SimpleFabMenu(
            options = listOf(
                HomeFabOption.QRCodeGeneration,
                HomeFabOption.QRCodeScan,
            ),
            onClick = {}
        )
    }
}