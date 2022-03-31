package br.com.alexf.sevenDaysOfCode

import br.com.alexf.sevenDaysOfCode.webclient.MovieWebClient
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val client = MovieWebClient()
    val json = client.findTop250MoviesAsRawJson()
        .body()?.string()
    val movieTitles = json?.movieFieldsBy("title")
    println(movieTitles)
    val movieImages = json?.movieFieldsBy("image")
    println(movieImages)
}

fun String.movieFieldsBy(
    field: String
): List<String> {
    val jsonFieldsSplitted = splittJsonFields(this)
    return jsonFieldsSplitted.filter {
        it.matchesBy(field)
    }
}

fun splittJsonFields(json: String) = json
    .replace("\",\"", "\"','\"")
    .split("{", "}", "[", "]", "','")

private fun String.matchesBy(field: String) =
    this.matches(Regex("\"($field)\":\"((\\\\\"|[^\"])*)\""))
