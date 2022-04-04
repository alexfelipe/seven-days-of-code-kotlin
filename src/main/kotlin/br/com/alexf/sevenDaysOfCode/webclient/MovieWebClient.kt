package br.com.alexf.sevenDaysOfCode.webclient

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import br.com.alexf.sevenDaysOfCode.webclient.model.toMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.jetbrains.skia.Image
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.imageio.ImageIO

class MovieWebClient {

    private val service = RetrofitInit().moviesService

    fun findTop250Movies() = flow {
        val movies = withContext(CoroutineScope(IO).coroutineContext) {
            service.findTop250Movies().items.map { top250Details ->
                top250Details.toMovie()
            }
        }
        emit(movies)
    }

    suspend fun findTop250MoviesAsRawJson(): Response<ResponseBody> {
        return service.findTop250MoviesAsRawJson()
    }



}