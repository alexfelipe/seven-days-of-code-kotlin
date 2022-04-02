package br.com.alexf.sevenDaysOfCode.ui

import br.com.alexf.sevenDaysOfCode.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.measureTimeMillis

private const val HTML_PATH = "ui/html"
private const val CSS_PATH = "ui/css"

class MainPage {

    suspend fun createNewPage(movies: List<Movie>) {
        withContext(Dispatchers.IO) {
            createHtmlDir()
            writeHtml(movies)
            createCssDir()
            writeCss()
        }
    }

    private fun createCssDir(
        path: String = CSS_PATH
    ) = createIfNotExistsDir(path)

    private fun createHtmlDir(
        path: String = HTML_PATH
    ) = createIfNotExistsDir(path)

    private fun createIfNotExistsDir(path: String) {
        if (Files.notExists(Paths.get(path))) {
            Files.createDirectories(Paths.get(path))
        }
    }

    private fun writeCss() {
        println("creating css")
        createAndWriteFile(
            "$CSS_PATH/main.css",
            buildCss()
        )
    }

    private fun writeHtml(
        movies: List<Movie>
    ) = createAndWriteFile(
        "$HTML_PATH/index.html",
        buildHtml(movies)
    )

    private fun createAndWriteFile(
        path: String,
        content: String
    ) {
        println("Starting creation of $path file")
        val time = measureTimeMillis {
            val file = File(path)
            file.writeText(content)
            file.createNewFile()
        }
        println("$path file was created in: $time seconds")
    }

}

private fun buildCss(): String = """ 
    body {
        background-color: #35363a
    }

    .container {
        margin: auto;
        justify-content: center;
    }

    .movie-title {
        color: #ffffff;
        margin: 16px auto;
        font-style: bold;
        font-size: 1.5em;
    }

    .movie-image {
        margin: 0 auto;
    }

    .movie-rating-year {
        color: #ffffff;
        margin: 16px;
        font-size: 1.1em;
    }
""".trimIndent()

private fun buildHtml(movies: List<Movie>): String {
    val sb = StringBuilder()
    movies.forEach {
        sb.append(
            """ <div class="container">
                <div class="movie-title"> ${it.title} </div>
                <img class="movie-image" src="${it.image}" alt="${it.title}"/>
                <div class="movie-rating-year"> nota: ${it.rating} - ano: ${it.year} </div>
                </div>
            """
        )
    }

    return """
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/main.css">
        <title>Top 250 Movies</title>
    </head>
    <body>
        $sb
    </body>
    </html>
""".trimIndent()
}