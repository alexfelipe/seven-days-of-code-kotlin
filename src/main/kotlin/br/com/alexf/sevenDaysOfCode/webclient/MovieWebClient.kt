package br.com.alexf.sevenDaysOfCode.webclient

import retrofit2.Response
import okhttp3.ResponseBody

class MovieWebClient {

    private val service = RetrofitInit().moviesService

    suspend fun findTop250MoviesAsRawJson(): Response<ResponseBody> {
        return service.findTop250MoviesAsRawJson()
    }

}