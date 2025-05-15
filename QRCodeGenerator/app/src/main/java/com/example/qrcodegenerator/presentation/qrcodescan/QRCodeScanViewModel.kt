package com.example.qrcodegenerator.presentation.qrcodescan

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class QRCodeScanViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(QRScanState())
    val state = _state.asStateFlow()

    val actions = QRCodeScanActions(
        onQRCodeScanned = ::onQRCodeScanned,
    )

    private fun onQRCodeScanned(qrCode: String) {
        _state.value = _state.value.copy(
            qrCodeRaw = qrCode,
        )
    }
}

data class QRScanState(
    val qrCodeRaw: String? = null,
)

data class QRCodeScanActions(
    val onQRCodeScanned: (String) -> Unit = {},
)
