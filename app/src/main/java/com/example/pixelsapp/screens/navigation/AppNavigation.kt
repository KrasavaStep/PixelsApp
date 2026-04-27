package com.example.pixelsapp.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pixelsapp.screens.home_screen.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Home.route
    ) {
        //Home screen
        composable(Route.Home.route) {
            HomeScreen()
            //val viewModel: HomeScreenViewModel = koinViewModel(qualifier = named("home_vm"))
            //HomeScreen(viewModel = viewModel, onCategoryClick = { tag ->
                //navController.navigate(Route.ItemList.createRoute(tag))
            //})
        }

        // List screen
        composable(
            route = Route.ItemList.route
        ) { backStackEntry ->
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
        }
    }
}