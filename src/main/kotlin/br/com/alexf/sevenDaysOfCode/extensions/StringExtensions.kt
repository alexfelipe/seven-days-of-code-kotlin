package br.com.alexf.sevenDaysOfCode.extensions

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
