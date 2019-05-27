package br.com.wsilva.tmdbexplorer.features.moviedetail.di

import br.com.wsilva.tmdbexplorer.di.AppModule
import br.com.wsilva.tmdbexplorer.features.moviedetail.DetailMovieFragment
import br.com.wsilva.tmdbexplorer.features.moviedetail.DetailMoviePresenter
import dagger.Component

@Component(modules = [DetailMovieModule::class, AppModule::class])
interface DetailMovieComponent {
    fun inject(fragment: DetailMovieFragment)
    fun inject(presenter: DetailMoviePresenter)
}