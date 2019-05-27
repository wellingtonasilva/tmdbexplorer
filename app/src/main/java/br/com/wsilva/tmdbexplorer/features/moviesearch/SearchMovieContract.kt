package br.com.wsilva.tmdbexplorer.features.moviesearch

import br.com.wsilva.tmdbexplorer.model.dto.SearchMovieGeneralDTO

interface SearchMovieContract {

    interface View {
        fun showMovie(list: List<SearchMovieGeneralDTO>)
        fun showDetail(movieId: Int)
        fun showMessage(value: Boolean)
    }

    interface Presenter {
        fun onQueryTextSubmit(query: String)
        fun onDestroy()
    }
}