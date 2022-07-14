package edu.uchicago.gerber.favs.presentation.search.widgets

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import edu.uchicago.gerber.books.navagation.Screen
import edu.uchicago.gerber.books.viewmodels.BookViewModel
import edu.uchicago.gerber.favs.screens.BookRow


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BookList(bookViewModel: BookViewModel, navController: NavController) {

    val lazyPagingItems = bookViewModel.searchState.value.data?.collectAsLazyPagingItems()

    LazyColumn {
        items(lazyPagingItems!!) { book ->
            BookRow(book = book!!) {
                //the following lines define the onItemClick behavior
                bookViewModel.setBusiness(book)
                navController.navigate(
                    route = Screen.Detail.route
                )
            }
        }

        lazyPagingItems.apply {
            //fallthrough is not supported
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Spinner()
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        Spinner()
                    }
                }
                loadState.prepend is LoadState.Loading -> {
                    item {
                        Spinner()
                    }
                }
            }
        }
    }
}
@Composable
fun Spinner(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(12.dp)
                .align(
                    Alignment.Center
                )
        )
    }
}