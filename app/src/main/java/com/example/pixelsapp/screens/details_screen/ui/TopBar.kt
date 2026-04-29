package com.example.pixelsapp.screens.details_screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pixelsapp.screens.details_screen.DetailsViewModel
import com.example.pixelsapp.screens.home_screen.ui.LoadingBar

@Composable
fun TopBar(
    viewModel: DetailsViewModel,
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.background(MaterialTheme.colorScheme.surface, CircleShape)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onBackground
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val photoData =
                viewModel.photoState.collectAsStateWithLifecycle().value.photoData
            Text(
                text = photoData?.photographer ?: "",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = photoData?.altName?.ifEmpty { "No description" } ?: "",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
        }
        if (viewModel.photoState.collectAsStateWithLifecycle().value.loading) {
            LoadingBar()
        } else {
            Spacer(modifier = Modifier.width(48.dp))
        }
    }
}