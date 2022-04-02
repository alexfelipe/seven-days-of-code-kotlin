package br.com.alexf.sevenDaysOfCode

import br.com.alexf.sevenDaysOfCode.ui.MainPage
import br.com.alexf.sevenDaysOfCode.webclient.MovieWebClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {
    val client = MovieWebClient()
    client.findTop250MoviesAsRawJson()
        .body()?.string()?.let { json ->
            printSomeFieldsFromMovieRawJson(json)
        }

    MainPage()
        .createNewPage(client.findTop250Movies())
}