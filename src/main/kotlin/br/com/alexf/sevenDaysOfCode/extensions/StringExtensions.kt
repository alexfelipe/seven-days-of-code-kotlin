package br.com.alexf.sevenDaysOfCode.extensions

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.imageio.ImageIO

fun String.movieFieldsBy(
    field: String
): List<String> {
    val jsonFieldsSplitted = this.splittJsonFields()
    return jsonFieldsSplitted.filter {
        it.matchesBy(field)
    }
}

fun String.matchesBy(field: String) =
    this.matches(Regex("\"($field)\":\"((\\\\\"|[^\"])*)\""))

fun String.splittJsonFields() =
    this.replace("\",\"", "\"','\"")
        .split("{", "}", "[", "]", "','")

fun String.loadNetworkImage(): ImageBitmap {
    val url = URL(this)
    val connection = url.openConnection() as HttpURLConnection
    connection.connect()

    val inputStream = connection.inputStream
    val bufferedImage = ImageIO.read(inputStream)

    val stream = ByteArrayOutputStream()
    ImageIO.write(bufferedImage, "png", stream)
    val byteArray = stream.toByteArray()

    return Image.makeFromEncoded(byteArray).toComposeImageBitmap()
}