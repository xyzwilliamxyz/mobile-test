package com.example.qrcodegenerator.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.qrcodegenerator.core.navigation.ScreenRoute
import com.example.qrcodegenerator.core.theme.QRCodeGeneratorTheme
import com.example.qrcodegenerator.presentation.home.HomeScreen
import com.example.qrcodegenerator.presentation.qrcodegeneration.QRCodeGenerationScreen
import com.example.qrcodegenerator.presentation.qrcodescan.QRCodeScanScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QRCodeGeneratorTheme {
                App()
            }
        }
    }
}

@Composable
private fun App() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = ScreenRoute.Home.route) {
        composable(ScreenRoute.Home.route) {
            HomeScreen(
                onNavigation = { route ->
                    navController.navigate(route)
                },
            )
        }
        composable(ScreenRoute.QRCodeGeneration.route) {
            QRCodeGenerationScreen(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
        composable(ScreenRoute.QRCodeScan.route) {
            QRCodeScanScreen(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}
