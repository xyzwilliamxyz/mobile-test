package com.example.qrcodegenerator.presentation.qrcodescan

import android.Manifest
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.qrcodegenerator.R
import com.example.qrcodegenerator.core.theme.QRCodeGeneratorTheme
import com.example.qrcodegenerator.presentation.component.permission.WithPermission
import com.example.qrcodegenerator.presentation.component.topbar.QRCGTopBar
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import timber.log.Timber

@Composable
fun QRCodeScanScreen(
    viewModel: QRCodeScanViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val state = viewModel.state.collectAsState().value
    val actions = viewModel.actions

    QRCodeScanScreenInternal(onBackClick, state, actions)
}

@Composable
private fun QRCodeScanScreenInternal(onBackClick: () -> Unit, state: QRScanState, actions: QRCodeScanActions) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.qrCodeRaw) {
        state.qrCodeRaw?.let { snackbarHostState.showSnackbar(it) }
    }

    Scaffold(
        topBar = {
            QRCGTopBar(
                title = stringResource(R.string.qr_code_generation_title),
                onBackClick = onBackClick,
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WithPermission(
                permission = Manifest.permission.CAMERA,
                rationaleMessage = stringResource(R.string.qr_code_scan_camera_permission_is_required)
            ) {
                Spacer(Modifier.height(64.dp))
                CameraPreviewBox(
                    modifier = Modifier.size(350.dp),
                    actions = actions
                )
            }
        }
    }
}

@Composable
private fun CameraPreviewBox(modifier: Modifier = Modifier, actions: QRCodeScanActions) {
    val lifecycleOwner = LocalLifecycleOwner.current

    AndroidView(
        modifier = modifier
            .aspectRatio(1f)
            .border(BorderStroke(8.dp, MaterialTheme.colorScheme.onBackground))
            .clip(CutCornerShape(0.dp))
            ,
        factory = { context ->
            val previewView = PreviewView(context).apply {
                scaleType = PreviewView.ScaleType.FILL_CENTER
            }

            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()

                val preview = androidx.camera.core.Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                val imageAnalyzer = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(ContextCompat.getMainExecutor(context)) { imageProxy ->
                            processImageProxyWithMlKit(imageProxy, actions)
                        }
                    }

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageAnalyzer
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, ContextCompat.getMainExecutor(context))

            previewView
        }
    )
}

@OptIn(ExperimentalGetImage::class)
private fun processImageProxyWithMlKit(imageProxy: ImageProxy, actions: QRCodeScanActions) {
    val mediaImage = imageProxy.image ?: run {
        imageProxy.close()
        return
    }

    val inputImage = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
    val scanner = BarcodeScanning.getClient()

    scanner.process(inputImage)
        .addOnSuccessListener { barcodes ->
            for (barcode in barcodes) {
                if (barcode.format == Barcode.FORMAT_QR_CODE) {
                    Timber.d("QR Code found: ${barcode.rawValue}")
                    actions.onQRCodeScanned(barcode.rawValue.orEmpty())
                }
            }
        }
        .addOnFailureListener { ex ->
            Timber.e(ex, "Scanning failed")
        }
        .addOnCompleteListener {
            imageProxy.close()
        }
}


@Preview
@Composable
private fun QRCodeScanScreen_Preview() {
    QRCodeGeneratorTheme {
        QRCodeScanScreenInternal(
            onBackClick = {},
            state = QRScanState(),
            actions = QRCodeScanActions()
        )
    }
}