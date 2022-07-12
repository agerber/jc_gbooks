package edu.uchicago.gerber.favs.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import edu.uchicago.gerber.books.models.Item
import edu.uchicago.gerber.favs.R


@ExperimentalAnimationApi
@Composable
fun BookRow(
    book: Item,
    onItemClick: (String) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                book.id?.let {
                    onItemClick(it)
                }
            }
            .padding(10.dp, 5.dp, 5.dp, 10.dp),
        shape = RoundedCornerShape(corner = CornerSize(4.dp)),
        elevation = 12.dp,
        backgroundColor = MaterialTheme.colors.surface

    ) {
        Row(horizontalArrangement = Arrangement.Start) {

            Surface(modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)) {

                GlideImage(
                    modifier = Modifier
                        .width(60.dp)
                        .height(90.dp),
                    imageModel = getSmallImageUrl(book),
                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                    contentScale = ContentScale.FillHeight,
                    placeHolder = painterResource(id = R.drawable.ic_placeholder),

                )


            }

            Column() {

                Text(
                    text = "${book.volumeInfo?.authors?.get(0)}",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp
                )
                //  Text(text = "${item.location?.displayAddress}")
            }
        }
    }
}

//null check the images
private fun getSmallImageUrl(book: Item): String {
    if (book.volumeInfo.imageLinks == null
        || book.volumeInfo.imageLinks.smallThumbnail == null ){
        return  "https://picsum.photos/123/picsum/60/90"
    } else {
        return book.volumeInfo.imageLinks.smallThumbnail
    }

}