package com.example.pixelsapp.screens.bookmarks_screen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.util.SourceVariants
import com.example.pixelsapp.screens.bookmarks_screen.BookmarksIntent
import com.example.pixelsapp.screens.bookmarks_screen.BookmarksViewModel
import com.example.pixelsapp.screens.home_screen.ui.ShimmerGrid

@Composable
fun BookmarksScreen(
    viewModel: BookmarksViewModel,
    onPhotoClick: (id: Int, source: String) -> Unit,
    onBackClick: () -> Unit
) {
    viewModel.handleIntent(BookmarksIntent.LoadData())

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Bookmarks",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    ) { padding ->
        if (state.isLoading) {
            ShimmerGrid()
        }
        else if (state.isEmpty) {
            EmptyBookmarkScreen {
                onBackClick()
            }
        } else {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalItemSpacing = 16.dp
            ) {
                items(state.data) { photo ->
                    BookmarkPhotoCard(
                        photo = photo
                    ) { onPhotoClick(photo.id, SourceVariants.LOCAL.source) }
                }
            }
        }
    }
}