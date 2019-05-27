package br.com.wsilva.tmdbexplorer.service

import br.com.wsilva.tmdbexplorer.model.dto.SearchMovieDTO
import br.com.wsilva.tmdbexplorer.model.dto.SearchMovieGeneralDTO
import io.reactivex.Single
import retrofit2.Retrofit

class BaseApiService(private val retrofit: Retrofit, private val apiKey: String)
{
    companion object {
        val TAG = "BaseApiService"
    }

    fun searchMovie(query: String): Single<SearchMovieDTO> {
        val api = retrofit.create(RestApi::class.java)
        return api.searchMovie(apiKey, query)
    }

    fun getMovieDetails(movieId: Int): Single<SearchMovieGeneralDTO> {
        val api = retrofit.create(RestApi::class.java)
        return api.getMovieDetails(movieId, apiKey)
    }
}