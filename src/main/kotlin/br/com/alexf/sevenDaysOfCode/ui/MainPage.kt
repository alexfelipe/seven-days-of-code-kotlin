package br.com.alexf.sevenDaysOfCode.ui

import br.com.alexf.sevenDaysOfCode.ui.model.PageContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.measureTimeMillis

private const val HTML_PATH = "ui/html"
private const val CSS_PATH = "ui/css"

class MainPage(
    private val content: PageContent
) {

    suspend fun createPage() {
        withContext(Dispatchers.IO) {
            createHtmlDir()
            writeHtml()
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
            content.css()
        )
    }

    private fun writeHtml(
    ) = createAndWriteFile(
        "$HTML_PATH/index.html",
        content.html()
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