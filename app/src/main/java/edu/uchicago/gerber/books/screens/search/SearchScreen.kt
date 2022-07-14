package edu.uchicago.gerber.favs.presentation.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import edu.uchicago.gerber.favs.presentation.search.SearchOperation
import edu.uchicago.gerber.favs.presentation.search.widgets.BookList

import edu.uchicago.gerber.favs.screens.CustomTextField
import edu.uchicago.gerber.books.viewmodels.BookViewModel
import edu.uchicago.gerber.favs.presentation.screens.details.DetailsScreen


@Composable
fun SearchScreen(
    bookViewModel: BookViewModel,
    navController: NavController,
) {
    val state = bookViewModel.searchState.value
    val queryText = bookViewModel.queryText.value

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 1.dp,

                ) {
                Text(
                    text = "Search Books",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )

            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            CustomTextField(
                title = "Search term(s)",
                placeHolder = "e.g. java",
                textState = queryText,
                onTextChange = bookViewModel::setQueryText,
                keyboardType = KeyboardType.Text,
                ImeAction.Search,
                bookViewModel::onSearch
            )

            Spacer(modifier = Modifier.height(10.dp))
            when (state.searchOperation) {
                SearchOperation.LOADING -> {
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
                SearchOperation.DONE -> {
                    BookList(bookViewModel, navController)
                }
                else -> {
                    Box {}
                }
            }

        }
    }
}