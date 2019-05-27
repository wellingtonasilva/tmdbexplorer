package br.com.wsilva.tmdbexplorer.features.moviedetail.di

import br.com.wsilva.tmdbexplorer.features.moviedetail.DetailMovieContract
import br.com.wsilva.tmdbexplorer.features.moviedetail.DetailMovieFragment
import br.com.wsilva.tmdbexplorer.features.moviedetail.DetailMoviePresenter
import br.com.wsilva.tmdbexplorer.service.BaseApiService
import br.com.wsilva.tmdbexplorer.util.AppUtils
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class DetailMovieModule(private val view: DetailMovieFragment) {

    @Provides
    fun providesDetailMovieView(): DetailMovieContract.View {
        return view
    }

    @Provides
    fun providesRetrofit(@Named("baseUrl") url: String): Retrofit {
        return AppUtils.createRetrofit(url)
    }

    @Provides
    fun providesBaseApiService(retrofit: Retrofit, @Named("apiKey") apiKey: String): BaseApiService {
        return BaseApiService(retrofit, apiKey)
    }

    @Provides
    fun providesDetailMoviePresenter(view: DetailMovieContract.View,
                                     baseApiService: BaseApiService): DetailMovieContract.Presenter
    {
        return DetailMoviePresenter(view, baseApiService)
    }
}