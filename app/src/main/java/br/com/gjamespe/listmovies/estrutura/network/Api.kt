package br.com.gjamespe.listmovies.estrutura.network

import br.com.gjamespe.listmovies.estrutura.network.modelo.Filme
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {

    @GET("trending/movie/week?api_key=7a177f35dbc7e7eca2c31989a63f232a&page=1&language=pt-BR")
    fun fetchTrendingMovies() : Call<Filme.ApiResponse>

    object Webservice {

        private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun filmeService() : Api = retrofit.create(Api::class.java)

    }

}