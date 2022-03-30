package br.com.alexf.sevenDaysOfCode

import br.com.alexf.sevenDaysOfCode.webclient.RetrofitInit
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val movies = RetrofitInit().moviesService.findTop250Movies()
    println(movies.body())
}