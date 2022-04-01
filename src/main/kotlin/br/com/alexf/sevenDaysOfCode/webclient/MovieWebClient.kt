package br.com.alexf.sevenDaysOfCode.webclient

import br.com.alexf.sevenDaysOfCode.model.Movie
import br.com.alexf.sevenDaysOfCode.webclient.model.toMovie
import retrofit2.Response
import okhttp3.ResponseBody

class MovieWebClient {

    private val service = RetrofitInit().moviesService

    suspend fun findTop250Movies(): List<Movie> {
        return service.findTop250Movies().items.map { top250Details ->
            top250Details.toMovie()
        }
    }

    suspend fun findTop250MoviesAsRawJson(): Response<ResponseBody> {
        return service.findTop250MoviesAsRawJson()
    }

}