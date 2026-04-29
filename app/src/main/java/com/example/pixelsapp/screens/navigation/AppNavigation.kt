package com.example.pixelsapp.screens.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pixelsapp.screens.details_screen.ui.DetailsScreen
import com.example.pixelsapp.screens.details_screen.DetailsViewModel
import com.example.pixelsapp.screens.home_screen.ui.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

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


        //composable(
            //route = Route.ItemList.route
        //) { backStackEntry ->
            //val tag = backStackEntry.arguments?.getString("tag_category") ?: ""
            //val category = CategoryName.valueOf(tag.toUpperCase(Locale.current))
            //val viewModel: ItemListViewModel =
            //    koinViewModel(qualifier = named("list_vm")) { parametersOf(category) }
            //ListScreen(
            //    viewModel = viewModel,
            //   onBackClick = { navController.popBackStack() },
            //    onItemClick = { detailsTag, id ->
            //        navController.navigate(Route.Details.createRoute(detailsTag, id.id))
             //   }
            //)
       // }
    }
}