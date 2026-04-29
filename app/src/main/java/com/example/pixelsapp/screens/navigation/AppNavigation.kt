package com.example.pixelsapp.screens.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pixelsapp.screens.bookmarks_screen.BookmarksViewModel
import com.example.pixelsapp.screens.bookmarks_screen.ui.BookmarksScreen
import com.example.pixelsapp.screens.details_screen.DetailsViewModel
import com.example.pixelsapp.screens.details_screen.ui.DetailsScreen
import com.example.pixelsapp.screens.home_screen.ui.HomeScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.route
    ) {
        //Home screen
        composable(Route.Home.route) {
            HomeScreen() { id, source ->
                navController.navigate(Route.Details.createRoute(id, source))
            }
        }

        // Details screen
        composable(
            route = Route.Details.route,
            arguments = listOf(
                navArgument("photo_id") { type = NavType.IntType },
                navArgument("source") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val viewModel: DetailsViewModel = hiltViewModel()
            DetailsScreen(
                viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        // Bookmarks screen
        composable(
            route = Route.Bookmarks.route
        ) { backStackEntry ->
            val viewModel: BookmarksViewModel = hiltViewModel()
            BookmarksScreen(
                viewModel,
                onBackClick = { navController.popBackStack() },
                onPhotoClick = { id, source ->
                    navController.navigate(Route.Details.createRoute(id, source))
                }
            )
        }

    }
}