package com.example.pixelsapp.screens.home_screen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pixelsapp.screens.home_screen.HomeViewModel
import com.example.pixelsapp.utils.shimmerEffect

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val pagedPhotos = uiState.photosPagingData.collectAsLazyPagingItems()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.isOffline) {
        if (uiState.isOffline) {
            snackbarHostState.showSnackbar("Отсутствует подключение к интернету. Показываем кэш.")
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {

            CollectionRow(
                collections = uiState.collections,
                selectedCollection = uiState.selectedCollection,
                onCollectionClick = { viewModel.onCategorySelected(it) }
            )

            Box(modifier = Modifier.fillMaxSize()) {
                when (pagedPhotos.loadState.refresh) {
                    is LoadState.Loading if pagedPhotos.itemCount == 0 -> {
                        ShimmerGrid()
                    }

                    is LoadState.Error if pagedPhotos.itemCount == 0 -> {
                        NetworkErrorScreen { pagedPhotos.retry() }
                    }

                    is LoadState.NotLoading if pagedPhotos.itemCount == 0 -> {

                    }

                    else -> {

                        //SearchBar(modifier = Modifier.padding(16.dp))

                        if (uiState.isCollectionsLoading || pagedPhotos.loadState.refresh is LoadState.Loading) {
                            LoadingBar()
                        } else {
                            Spacer(modifier = Modifier.height(2.dp))
                        }

                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Fixed(2),
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalItemSpacing = 16.dp
                        ) {
                            items(pagedPhotos.itemCount) { index ->
                                pagedPhotos[index]?.let { photo ->
                                    PhotoCard(photo)
                                }
                            }

                            if (pagedPhotos.loadState.append is LoadState.Loading) {
                                items(2) {
                                    Box(
                                        modifier = Modifier
                                            .height(300.dp)
                                            .fillMaxWidth()
                                            .shimmerEffect()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}