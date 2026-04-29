package com.example.pixelsapp.screens.details_screen.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.pixelsapp.screens.details_screen.DetailsViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImgItem(
    viewModel: DetailsViewModel
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