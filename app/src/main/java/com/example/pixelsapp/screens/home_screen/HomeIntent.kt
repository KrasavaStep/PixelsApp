package com.example.pixelsapp.screens.home_screen

sealed class HomeIntent {

    class OnSearchQueryChanged(val query: String): HomeIntent()

    class OnSearchClicked: HomeIntent()

    class OnCategorySelected(val category: String): HomeIntent()

    class ClearSearch: HomeIntent()

    class LoadInitialData: HomeIntent()

}