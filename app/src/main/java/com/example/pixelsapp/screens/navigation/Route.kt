package com.example.pixelsapp.screens.navigation

sealed class Route(val route: String) {
    object Home : Route("home")
    object ItemList : Route("items/{tag_category}") {
        fun createRoute(tag: String) = "items/$tag"
    }
    object Details : Route("details/{details_tag}/{details_id}") {
        fun createRoute(tag: String, id: Int) = "details/$tag/$id"
    }
}