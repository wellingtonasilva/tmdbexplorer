package br.com.wsilva.tmdbexplorer.features.moviesearch.di

import br.com.wsilva.tmdbexplorer.di.AppModule
import br.com.wsilva.tmdbexplorer.features.moviesearch.SearchMovieFragment
import br.com.wsilva.tmdbexplorer.features.moviesearch.SearchMoviePresenter
import dagger.Component

@Component(modules = [SearchMovieModule::class, AppModule::class])
interface SearchMovieComponent {
    fun inject(fragment: SearchMovieFragment)
    fun inject(presenter: SearchMoviePresenter)
}