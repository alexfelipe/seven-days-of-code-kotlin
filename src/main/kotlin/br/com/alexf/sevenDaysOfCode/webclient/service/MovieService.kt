package br.com.alexf.sevenDaysOfCode.webclient.service

import br.com.alexf.sevenDaysOfCode.IMDB_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {

    @GET("API/Top250Movies/$IMDB_KEY")
    suspend fun findTop250MoviesAsRawJson(): Response<ResponseBody>

}
