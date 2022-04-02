package br.com.alexf.sevenDaysOfCode

import br.com.alexf.sevenDaysOfCode.extensions.movieFieldsBy
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class JsonExtractionTest {

    private val someJsonFields =
        """
           {"items" : [
            {"title":"Some title for testing","image":"https://m.media-amazon.com/images/M/MV5BNjJlYmNkZGItM2NhYy00MjlmLTk5NmQtNjg1NmM2ODU4OTMwXkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_UX128_CR0,3,128,176_AL_.jpg"},
            {"title":"Some title for testing2","image":"https://m.media-amazon.com/images/M/MV5BNjJlYmNkZGItM2NhYy00MjlmLTk5NmQtNjg1NmM2ODU4OTMwXkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_UX128_CR0,3,128,176_AL_.jpg"}
           ]}
        """.trimIndent()

    @Test
    fun shouldGetMovieTitlesExpected() {
        val jsonTitles = someJsonFields.movieFieldsBy("title")

        val expectedMovieTitles =
            listOf(
                """"title":"Some title for testing"""",
                """"title":"Some title for testing2""""
            )

        Assertions.assertEquals(
            expectedMovieTitles,
            jsonTitles
        )
    }

    @Test
    fun shouldGetMovieImagesExpected() {
        val jsonImages = someJsonFields.movieFieldsBy("image")

        val expectedMovieImages =
            listOf(
                """"image":"https://m.media-amazon.com/images/M/MV5BNjJlYmNkZGItM2NhYy00MjlmLTk5NmQtNjg1NmM2ODU4OTMwXkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_UX128_CR0,3,128,176_AL_.jpg"""",
                """"image":"https://m.media-amazon.com/images/M/MV5BNjJlYmNkZGItM2NhYy00MjlmLTk5NmQtNjg1NmM2ODU4OTMwXkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_UX128_CR0,3,128,176_AL_.jpg""""
            )

        Assertions.assertEquals(
            expectedMovieImages,
            jsonImages
        )
    }

}