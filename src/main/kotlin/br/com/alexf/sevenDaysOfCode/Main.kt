package br.com.alexf.sevenDaysOfCode

import br.com.alexf.sevenDaysOfCode.ui.MainPage
import br.com.alexf.sevenDaysOfCode.ui.model.Top250MoviesContent
import br.com.alexf.sevenDaysOfCode.webclient.MovieWebClient
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val client = MovieWebClient()
//    client.findTop250MoviesAsRawJson()
//        .body()?.string()?.let { json ->
//            printSomeFieldsFromMovieRawJson(json)
//        }
    val movies = client.findTop250Movies()
    MainPage(Top250MoviesContent(*movies.toTypedArray())).createPage()
}