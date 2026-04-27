package com.example.pixelsapp.screens.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pixelsapp.utils.shimmerEffect

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val photos = viewModel.photos.collectAsLazyPagingItems()
    val categories = listOf("Ice", "Watches", "Drawing", "Brick")

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        //SearchBar(modifier = Modifier.padding(16.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                CategoryChip(category, isSelected = category == "Ice")
            }
        }

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalItemSpacing = 16.dp
        ) {
            items(photos.itemCount) { index ->
                photos[index]?.let { photo ->
                    PhotoCard(photo)
                }
            }

            when (photos.loadState.append) {
                is LoadState.Loading -> {
                    items(2) {
                        Box(modifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth()
                            .shimmerEffect()
                        )
                    }
                }

                is LoadState.Error -> {
                    items(1) {
                        Text("Error")
                    }
                }

                else -> {}
            }
        }
    }
}