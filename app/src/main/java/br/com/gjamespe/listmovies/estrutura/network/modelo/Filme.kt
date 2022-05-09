package br.com.gjamespe.listmovies.estrutura.network.modelo

import java.io.Serializable

data class Filme(val id: Int,
            val title: String,
            val original_title: String,
            val original_language: String,
            val release_date: String,
            val popularity: Double,
            val vote_count: Int,
            val vote_average: Double,
            val adult: Boolean,
            val video: Boolean,
            val genre_ids: Array<Int>,
            val overview: String,
            val backdrop_path: String,
            val poster_path: String,
            val media_type: String) : Serializable {

    fun Capa() : String {
        return "https://image.tmdb.org/t/p/w500$poster_path"
    }

    fun Titulo() : String {
        return title
    }

    fun DataLancamento() : String {
        return release_date
    }

    fun Avaliacao() : String {
        return "$vote_average/10"
    }

    fun Sinopse() : String {
        return overview
    }
    class ApiResponse(val page: Int,
                      val total_results: Int,
                      val total_pages: Int,
                      val results: ArrayList<Filme>)

}