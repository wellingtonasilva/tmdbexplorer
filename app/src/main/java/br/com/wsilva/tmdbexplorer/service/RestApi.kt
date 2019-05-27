package br.com.wsilva.tmdbexplorer.service

import br.com.wsilva.tmdbexplorer.model.dto.SearchMovieDTO
import br.com.wsilva.tmdbexplorer.model.dto.SearchMovieGeneralDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {
    @GET("search/movie")
    fun searchMovie(@Query("api_key") apiKey: String,
                    @Query("query") query: String,
                    @Query("page") page: Int = 1,
                    @Query("language") language: String = "en-US",
                    @Query("include_adult") includeAdult: Boolean = true,
                    @Query("region") region: String? = null,
                    @Query("year") year: Int? = null,
                    @Query("primary_release_year") primaryReleaseYear: Int? = null): Single<SearchMovieDTO>


    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int,
                        @Query("api_key") apiKey: String): Single<SearchMovieGeneralDTO>
}