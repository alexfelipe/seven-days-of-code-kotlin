package br.com.alexf.sevenDaysOfCode.ui.model

import br.com.alexf.sevenDaysOfCode.model.Movie

class Top250MoviesContent(
    private vararg val movies: Movie
) : PageContent {

    override fun title(): String {
        return "Top 250 Movies"
    }

    override fun html(): String = """
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/main.css">
        <title>${title()}</title>
    </head>
    <body>
        ${body()}
    </body>
    </html>
""".trimIndent()

    override fun body(): String {
        println(movies.size)
        return StringBuilder().run {
            movies.forEach {
                this.append(
                    """ <div class="container">
                <div class="movie-title"> ${it.title} </div>
                <img class="movie-image" src="${it.image}" alt="${it.title}"/>
                <div class="movie-rating-year"> nota: ${it.rating} - ano: ${it.year} </div>
                </div>
                """.trimIndent()
                )
            }
            this.toString()
        }
    }

    override fun css(): String =
        """ 
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

}