package com.example.pixelsapp.screens.details_screen.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.pixelsapp.R
import com.example.pixelsapp.screens.details_screen.DetailsIntent
import com.example.pixelsapp.screens.details_screen.DetailsViewModel
import com.example.pixelsapp.screens.home_screen.ui.LoadingBar
import com.example.pixelsapp.ui.theme.Primary
import androidx.compose.runtime.collectAsState

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    onBackClick: () -> Unit
) {
    viewModel.handleIntent(DetailsIntent.LoadPhoto())

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.background(Color(0xFFF5F5F5), CircleShape)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val photoData = viewModel.photoState.collectAsStateWithLifecycle().value.photoData
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
        },
        bottomBar = {
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
                            Color(0xFFF5F5F5),
                            RoundedCornerShape(12.dp)
                        )
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "Save",
                            tint = Primary
                        )
                    }
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            val photoData = viewModel.photoState.collectAsStateWithLifecycle().value.photoData
            GlideImage(
                model = photoData?.url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(24.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}