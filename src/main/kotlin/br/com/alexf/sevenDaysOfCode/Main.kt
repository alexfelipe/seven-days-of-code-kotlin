package br.com.alexf.sevenDaysOfCode

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import br.com.alexf.sevenDaysOfCode.extensions.loadNetworkImage
import br.com.alexf.sevenDaysOfCode.webclient.MovieWebClient

private const val IMDB_ICON = "https://ia.media-imdb.com/images/M/MV5BODc4MTA3NjkzNl5BMl5BcG5nXkFtZTgwMDg0MzQ2OTE@._V1_.png"

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Top 250 filmes",
        state = rememberWindowState(),
        icon = BitmapPainter(IMDB_ICON.loadNetworkImage()),
    ) {
        val client = MovieWebClient()
        val movies by client.findTop250Movies().collectAsState(emptyList())
        MaterialTheme {
            LazyColumn (
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color(0xff35363a))
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
            ) {
                items(movies) { movie ->
                    Box(
                        modifier = Modifier.padding(8.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(Color(0xff444444))
                            .width(400.dp)
                    ) {
                        Column {
                            Text(
                                movie.title, modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Image(
                                bitmap = movie.image.loadNetworkImage(),
                                contentDescription = movie.title,
                                modifier = Modifier.fillMaxWidth()
                                    .height(200.dp),
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                "Nota: ${movie.rating} - Ano: ${movie.year}",
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}