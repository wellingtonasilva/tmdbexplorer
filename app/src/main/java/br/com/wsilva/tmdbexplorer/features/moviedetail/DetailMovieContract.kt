package br.com.wsilva.tmdbexplorer.features.moviedetail

import br.com.wsilva.tmdbexplorer.model.dto.SearchMovieGeneralDTO

interface DetailMovieContract {

    interface View {
        fun showDetailMovie(movie: SearchMovieGeneralDTO)
    }

    interface Presenter {
        fun loadDetailMovie(movieId: Int)
        fun onDestroy()
    }
}