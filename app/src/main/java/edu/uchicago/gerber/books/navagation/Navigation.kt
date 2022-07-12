package edu.uchicago.gerber.favs.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import edu.uchicago.gerber.favs.presentation.screens.details.DetailsScreen

import edu.uchicago.gerber.favs.presentation.screens.search.SearchScreen
import edu.uchicago.gerber.books.navagation.Screen
import edu.uchicago.gerber.books.viewmodels.BookViewModel


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    navController: NavHostController
) {

    val bookViewModel: BookViewModel = hiltViewModel()
    NavHost(navController, startDestination = Screen.Search.route) {
        composable(Screen.Search.route) {
            SearchScreen(bookViewModel = bookViewModel, navController = navController)

        }
        composable(Screen.Detail.route) { backStackEntry ->
            DetailsScreen(navController = navController, bookViewModel = bookViewModel)
        }
    }
}

