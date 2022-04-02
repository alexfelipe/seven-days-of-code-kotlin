package br.com.alexf.sevenDaysOfCode

import br.com.alexf.sevenDaysOfCode.extensions.movieFieldsBy

fun printSomeFieldsFromMovieRawJson(json: String) {
    val movieTitles = json.movieFieldsBy("title")
    println(movieTitles)
    val movieImages = json.movieFieldsBy("image")
    println(movieImages)
}