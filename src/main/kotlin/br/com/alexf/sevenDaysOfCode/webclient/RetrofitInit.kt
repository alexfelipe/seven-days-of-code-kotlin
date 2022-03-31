package br.com.alexf.sevenDaysOfCode.webclient

import br.com.alexf.sevenDaysOfCode.webclient.service.MovieService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInit {

    private val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BASIC)
    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://imdb-api.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val moviesService: MovieService
        get() = retrofit.create(MovieService::class.java)

}