package com.example.pixelsapp.screens.details_screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pixelsapp.R
import com.example.pixelsapp.screens.details_screen.DetailsIntent
import com.example.pixelsapp.screens.details_screen.DetailsViewModel

@Composable
fun BottomBar(
    viewModel: DetailsViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val photoState = viewModel.photoState.collectAsStateWithLifecycle().value

        AnimatedDownloadButton {
            viewModel.handleIntent(
                DetailsIntent.DownloadPhoto(
                    photoState.photoData?.url ?: "",
                    photoState.photoData?.photographer ?: ""
                )
            )
        }

        photoState.let { state ->
            val icon = if (state.isLiked)
                ImageVector.vectorResource(R.drawable.ic_bookmark_filled)
            else ImageVector.vectorResource(R.drawable.ic_boomark)
            IconButton(
                onClick = {
                    viewModel.handleIntent(DetailsIntent.ToggleLike(state.isLiked))
                },
                modifier = Modifier.background(
                    MaterialTheme.colorScheme.surface,
                    RoundedCornerShape(12.dp)
                )
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = stringResource(R.string.icon_save_description),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}