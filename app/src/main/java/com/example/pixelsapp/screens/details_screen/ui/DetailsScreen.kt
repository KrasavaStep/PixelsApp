package com.example.pixelsapp.screens.details_screen.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pixelsapp.screens.details_screen.DetailsIntent
import com.example.pixelsapp.screens.details_screen.DetailsViewModel

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    onBackClick: () -> Unit
) {
    viewModel.handleIntent(DetailsIntent.LoadPhoto())

    val isVisible =
        viewModel.photoState.collectAsStateWithLifecycle().value.errorMessage.isNullOrEmpty()

    Scaffold(
        topBar = {
            TopBar(viewModel) {
                onBackClick()
            }
        },
        bottomBar = {
            if (isVisible) {
                BottomBar(viewModel)
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            if (isVisible) {
                ImgItem(viewModel)
            } else {
                ImgNotFoundScreen {
                    onBackClick()
                }
            }
        }
    }
}
