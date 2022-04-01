package br.com.alexf.sevenDaysOfCode.webclient.model

import br.com.alexf.sevenDaysOfCode.model.Movie

data class Top250Data(
    val items: List<Top250DataDetail>,
    val errorMessage: String
)

data class Top250DataDetail(
    val id: String,
    val rank: String,
    val title: String,
    val fullTitle: String,
    val year: String,
    val image: String,
    val crew: String,
    val imDbRating: String,
    val imDbRatingCount: String
)

fun Top250DataDetail.toMovie(): Movie {
    return Movie(
        title = this.title,
        image = image,
        rating = imDbRating,
        year = year
    )
}