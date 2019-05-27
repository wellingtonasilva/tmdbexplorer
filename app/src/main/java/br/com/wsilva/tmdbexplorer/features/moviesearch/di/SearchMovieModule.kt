package br.com.wsilva.tmdbexplorer.features.moviesearch.di

import br.com.wsilva.tmdbexplorer.features.moviesearch.SearchMovieContract
import br.com.wsilva.tmdbexplorer.features.moviesearch.SearchMovieFragment
import br.com.wsilva.tmdbexplorer.features.moviesearch.SearchMoviePresenter
import br.com.wsilva.tmdbexplorer.service.BaseApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class SearchMovieModule (private val view: SearchMovieFragment)
{
    @Provides
    fun providesSearchMovieView(): SearchMovieContract.View
    {
        return view
    }

    @Provides
    fun providesBaseApiService(retrofit: Retrofit, @Named("apiKey") apiKey: String): BaseApiService {
        return BaseApiService(retrofit, apiKey)
    }

    @Provides
    fun providesSearchMoviePresenter(view: SearchMovieContract.View,
                                     baseApiService: BaseApiService): SearchMovieContract.Presenter
    {
        return SearchMoviePresenter(view, baseApiService)
    }
}