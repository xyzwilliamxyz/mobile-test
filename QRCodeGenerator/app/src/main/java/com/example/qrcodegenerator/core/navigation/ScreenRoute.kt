package com.example.qrcodegenerator.core.navigation

sealed class ScreenRoute(
    val route: String,
) {
    data object Home : ScreenRoute("home")
    data object QRCodeGeneration : ScreenRoute("generation")
    data object QRCodeScan : ScreenRoute("scan")
}
