package com.example.qrcodegenerator.presentation.component.permission

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun WithPermission(
    permission: String,
    rationaleMessage: String,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val permissionGranted = remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted ->
        permissionGranted.value = isGranted
        if (!isGranted) {
            Toast.makeText(context, rationaleMessage, Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(permission)
    }

    if (permissionGranted.value) {
        content()
    }
}
