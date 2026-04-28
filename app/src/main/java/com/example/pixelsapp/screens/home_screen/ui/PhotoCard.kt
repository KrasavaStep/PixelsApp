package com.example.pixelsapp.screens.home_screen.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.domain.model.PhotoResource
import com.example.pixelsapp.R
import com.example.pixelsapp.utils.shimmerEffect

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PhotoCard(photo: PhotoResource) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Log.e("HEIGHT_ERR", photo.height.toString())
        GlideImage(
            model = photo.url,
            contentDescription = "Pexels Image",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            loading = placeholder {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmerEffect()
                )
            },
            failure = placeholder(R.drawable.no_image)
        )
    }
}