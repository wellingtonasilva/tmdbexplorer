package br.com.wsilva.tmdbexplorer.model.dto

import com.google.gson.annotations.SerializedName
import java.util.*

data class SearchMovieGeneralDTO(@SerializedName("vote_count") val voteCount: Int,
                                 @SerializedName("id") val id: Int,
                                 @SerializedName("video") val video: Boolean,
                                 @SerializedName("vote_average") val voteAverage: Number,
                                 @SerializedName("title") val title: String,
                                 @SerializedName("popularity") val popularity: Number,
                                 @SerializedName("poster_path") val posterPath: String,
                                 @SerializedName("original_language") val original_language: String,
                                 @SerializedName("original_title") val original_title: String,
                                 @SerializedName("backdrop_path") val backdrop_path: String,
                                 @SerializedName("adult") val adult: Boolean,
                                 @SerializedName("overview") val overview: String,
                                 @SerializedName("release_date") val releaseDate: String,
                                 @SerializedName("genre_ids") val genreIds: List<Int>,
                                 @SerializedName("revenue") val revenue: Number,
                                 @SerializedName("budget") val budget: Number,
                                 @SerializedName("runtime") val runtime: Int
)
