package com.example.qrcodegenerator.presentation.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrcodegenerator.R
import com.example.qrcodegenerator.core.navigation.ScreenRoute
import com.example.qrcodegenerator.presentation.component.fabmenu.FabMenuOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _navigation = MutableSharedFlow<HomeNavigation>()
    val navigation = _navigation.asSharedFlow()

    val actions = HomeActions(
        onFacActionClick = ::onFabActionClick,
    )

    init {
        initialize()
    }

    private fun initialize() {
        _state.value = state.value.copy(
            fabOptions = listOf(
                HomeFabOption.QRCodeGeneration,
                HomeFabOption.QRCodeScan,
            ),
        )
    }

    private fun onFabActionClick(option: FabMenuOption) {
        val homeOption = option as HomeFabOption
        val destination = when (homeOption) {
            is HomeFabOption.QRCodeGeneration -> {
                HomeNavigation.QRCodeGeneration
            }
            is HomeFabOption.QRCodeScan -> {
                HomeNavigation.QRCodeScan
            }
        }
        viewModelScope.launch {
            _navigation.emit(destination)
        }
    }
}

data class HomeState(
    val fabOptions: List<HomeFabOption> = listOf(),
)

sealed class HomeFabOption(
    override val icon: ImageVector,
    @StringRes override val description: Int,
) : FabMenuOption {
    object QRCodeGeneration : HomeFabOption(
        icon = Icons.Default.AddCircle,
        description = R.string.home_fab_option_qrcode,
    )

    object QRCodeScan : HomeFabOption(
        icon = Icons.Default.AddCircle,
        description = R.string.home_fab_option_scan,
    )
}

sealed class HomeNavigation(
    val route: String,
) {
    object QRCodeGeneration : HomeNavigation(ScreenRoute.QRCodeGeneration.route)
    object QRCodeScan : HomeNavigation(ScreenRoute.QRCodeScan.route)
}

data class HomeActions(
    val onFacActionClick: (FabMenuOption) -> Unit = {},
)
