package com.example.qrcodegenerator.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.qrcodegenerator.R
import com.example.qrcodegenerator.core.theme.QRCodeGeneratorTheme
import com.example.qrcodegenerator.presentation.component.topbar.QRCGTopBar
import com.example.qrcodegenerator.presentation.component.fabmenu.SimpleFabMenu
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigation: (String) -> Unit
) {
    val state = viewModel.state.collectAsState().value
    val navigation = viewModel.navigation
    val actions = viewModel.actions

    HandleNavigation(navigation, onNavigation)
    HomeScreenInternal(state, actions)
}

@Composable
private fun HandleNavigation(
    navigationFlow: Flow<HomeNavigation>,
    onNavigation: (String) -> Unit,
) {
    val currentOnNavigation by rememberUpdatedState(onNavigation)

    LaunchedEffect(Unit) {
        navigationFlow.collectLatest { nav ->
            currentOnNavigation(nav.route)
        }
    }
}

@Composable
private fun HomeScreenInternal(state: HomeState, actions: HomeActions) {
    Scaffold(
        topBar = {
            QRCGTopBar(
                title = stringResource(R.string.home_title)
            )
        },
        floatingActionButton = {
            SimpleFabMenu(
                state.fabOptions,
                actions.onFacActionClick
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize().background(MaterialTheme.colorScheme.background)
        ) {

        }
    }
}

@Preview
@Composable
private fun HomeScreen_Preview() {
    QRCodeGeneratorTheme {
        HomeScreenInternal(
            state = HomeState(),
            actions = HomeActions()
        )
    }
}