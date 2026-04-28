package com.example.pixelsapp.screens.home_screen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import com.example.domain.model.FeaturedCollection
import java.util.Locale

@Composable
fun CollectionRow(
    collections: List<FeaturedCollection>,
    selectedCollection: String?,
    onCollectionClick: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        items(collections) { collection ->
            CollectionChip(
                collection = collection.title,
                isSelected = collection.title.equals(selectedCollection, ignoreCase = true),
                onClick = { onCollectionClick(collection.title) }
            )
        }
    }
}