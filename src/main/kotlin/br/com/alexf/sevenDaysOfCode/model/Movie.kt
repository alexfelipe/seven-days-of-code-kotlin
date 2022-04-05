package br.com.alexf.sevenDaysOfCode.model

data class Movie(
    val title: String,
    val image: String,
    val rating: String,
    val year: String
) : Comparable<Movie> {
    override fun compareTo(other: Movie): Int =
        this.rating.compareTo(other.rating)
}