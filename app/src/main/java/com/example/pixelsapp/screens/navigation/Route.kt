package com.example.pixelsapp.screens.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Route(val route: String, val icon: ImageVector? = null) {
    object Home : Route(
        route = "home",
        icon = Icons.Default.Home
    )
    object Details : Route(
        route = "details/{photo_id}/{source}"
    ) {
        fun createRoute(id: Int, source: String) = "details/$id/$source"
    }
    object Bookmarks : Route(
        route = "bookmarks",
        icon = Icons.Default.Bookmark
    )
}