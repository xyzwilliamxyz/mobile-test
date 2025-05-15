package com.example.qrcodegenerator.presentation.component.qrcode

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set
import com.example.qrcodegenerator.R
import com.example.qrcodegenerator.core.theme.QRCodeGeneratorTheme
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix

@Composable
fun QrCodeImage(seed: String, modifier: Modifier = Modifier) {
    val qrBitmap = remember(seed) { generateQrCodeBitmap(seed) }

    Image(
        bitmap = qrBitmap,
        contentDescription = stringResource(R.string.qr_code_for_seed_content_description),
        modifier = modifier,
    )
}

private fun generateQrCodeBitmap(seed: String, size: Int = 512): ImageBitmap {
    val bitMatrix: BitMatrix = MultiFormatWriter().encode(
        seed,
        BarcodeFormat.QR_CODE,
        size,
        size,
    )

    val bitmap = createBitmap(size, size)
    for (x in 0 until size) {
        for (y in 0 until size) {
            bitmap[x, y] = if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE
        }
    }
    return bitmap.asImageBitmap()
}

@Preview
@Composable
private fun QRCodeImage_Preview() {
    QRCodeGeneratorTheme {
        QrCodeImage(
            seed = "49e5982edac923b3b4f29ff9da179d8f",
        )
    }
}
