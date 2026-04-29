package com.example.pixelsapp.screens.navigation

sealed class Route(val route: String) {
    object Home : Route("home")
    object Details : Route("details/{photo_id}/{source}") {
        fun createRoute(id: Int, source: String) = "details/$id/$source"
    }
    object Bookmarks : Route("details/{details_tag}/{details_id}") {
        fun createRoute(tag: String, id: Int) = "details/$tag/$id"
    }
}