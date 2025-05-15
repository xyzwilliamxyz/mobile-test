package com.example.qrcodegenerator.presentation.qrcodegeneration

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.qrcodegenerator.R
import com.example.qrcodegenerator.core.theme.QRCodeGeneratorTheme
import com.example.qrcodegenerator.presentation.component.loading.LoadingIndicator
import com.example.qrcodegenerator.presentation.component.qrcode.QrCodeImage
import com.example.qrcodegenerator.presentation.component.topbar.QRCGTopBar

@Composable
fun QRCodeGenerationScreen(
    viewModel: QRCodeGenerationViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val state = viewModel.state.collectAsState().value

    QRCodeGenerationScreenInternal(state, onBackClick)
}

@Composable
private fun QRCodeGenerationScreenInternal(state: QRCodeGenerationState, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            QRCGTopBar(
                title = stringResource(R.string.qr_code_generation_title),
                onBackClick = onBackClick,
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            QRCodeSection(state)
            Spacer(Modifier.height(32.dp))
            Text(
                text = state.formatTimeRemaining(),
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

@Composable
private fun QRCodeSection(state: QRCodeGenerationState) {
    Box(
        modifier = Modifier.height(400.dp).fillMaxWidth(),
    ) {
        if (state.isLoading) {
            LoadingIndicator()
        }

        if (state.isSeedValid()) {
            QrCodeImage(
                modifier = Modifier
                    .padding(16.dp)
                    .size(400.dp),
                seed = state.seed,
            )
        }
    }
}

@Preview
@Composable
private fun QRCodeGenerationScreen_Preview() {
    QRCodeGeneratorTheme {
        QRCodeGenerationScreenInternal(
            state = QRCodeGenerationState(
                seed = "03274be7c8d2ef35b0026d88f257f300",
                timeRemaining = 15,
            ),
            onBackClick = {},
        )
    }
}
