package edu.uchicago.gerber.favs.presentation.screens.details


import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.skydoves.landscapist.glide.GlideImage
import edu.uchicago.gerber.books.models.Item
import edu.uchicago.gerber.favs.R
import edu.uchicago.gerber.books.viewmodels.BookViewModel


@Composable
fun DetailsScreen(
    navController: NavController,
    bookViewModel: BookViewModel
) {

    //observe the book
    val book = bookViewModel.book.value
    val context = (LocalContext.current as? Activity)


    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 1.dp
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back",
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                        .align(Alignment.CenterVertically)
                        .padding(20.dp, 0.dp, 0.dp, 0.dp))

                Text(
                    text = "Details",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {

                    Icon(imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        modifier = Modifier
                            .clickable {
                                val sendIntent = Intent(Intent.ACTION_SEND)
                                sendIntent.type = "text/plain"
                                sendIntent.putExtra(
                                    Intent.EXTRA_TEXT,
                                    book.volumeInfo.title
                                )
                                context?.startActivity(sendIntent)
                            }
                            .align(Alignment.CenterVertically)
                            .padding(10.dp, 0.dp, 0.dp, 0.dp))
                    Spacer(modifier = Modifier.width(20.dp))
                    Icon(painter = painterResource(id = R.drawable.ic_navigation),
                        contentDescription = "Map",

                        modifier = Modifier
                            .clickable {
                                //this would be used for navigating if you have a physical address
//                                val intent = Intent(
//                                    Intent.ACTION_VIEW,
//                                    Uri.parse(
//                                        "google.navigation:q=${business.location?.displayAddress}"
//                                    )
//                                )
//                                context?.startActivity(intent)
                            }
                            .align(Alignment.CenterVertically)
                            .padding(10.dp, 0.dp, 10.dp, 0.dp))
                    Spacer(modifier = Modifier.width(20.dp))
                    Icon(imageVector = Icons.Default.Phone,
                        contentDescription = "Phone",

                        modifier = Modifier
                            .clickable {
                                //this would be used for dialing if you have a phone number
//                                val intent = Intent(
//                                    Intent.ACTION_DIAL,
//                                    Uri.parse("tel:${business.displayPhone}")
//                                )
//                                context?.startActivity(intent)
                            }
                            .align(Alignment.CenterVertically)
                            .padding(0.dp, 0.dp, 20.dp, 0.dp))
                }

            }

        }
    }) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState(0))
                .padding(20.dp, 0.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {


                Divider()
                GlideImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(300.dp),
                    imageModel = book.volumeInfo?.imageLinks?.thumbnail ?: "https://picsum.photos/234/picsum/300/400",
                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                    contentScale = ContentScale.Fit

                )
                book.volumeInfo.title?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Start,
                        fontSize = 22.sp
                    )
                }

                book.volumeInfo.authors[0]?.let {
                    Text(
                        text = it.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(fontWeight = FontWeight.Normal),
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp
                    )
                }
                Button(
                    modifier =
                    Modifier
                        .padding(10.dp, 0.dp)
                        .fillMaxWidth(1f),

                    onClick = {
                        Toast.makeText(context, "Favorite Pressed", Toast.LENGTH_LONG).show()
                    },

                    colors =
                        ButtonDefaults.buttonColors(backgroundColor = Color.Green)

                ) {
                        Text(text = "Add to Favorites")
                }

            }
        }
    }

}




