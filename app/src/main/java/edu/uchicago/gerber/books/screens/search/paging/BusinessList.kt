package edu.uchicago.gerber.favs.presentation.search.widgets

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
fun BusinessList(bookViewModel: BookViewModel, navController: NavController) {

    val res = bookViewModel.searchState.value.data?.collectAsLazyPagingItems()

    LazyColumn {
        items(res!!) { book ->
            BookRow(book = book!!) {
                bookViewModel.setBusiness(book)
                navController.navigate(
                    route = Screen.Detail.route
                )
            }
        }

        res.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
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
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
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
                }
                loadState.prepend is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
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
                }
            }
        }
    }
}