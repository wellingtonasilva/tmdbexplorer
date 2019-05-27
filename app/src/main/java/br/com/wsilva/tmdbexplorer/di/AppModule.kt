package br.com.wsilva.tmdbexplorer.di

import br.com.wsilva.tmdbexplorer.constants.AppConstants
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AppModule {

    @Provides
    @Named("baseUrl")
    fun providesUrlSearhMovie() = AppConstants.BASE_URL

    @Provides
    @Named("apiKey")
    fun providesApiKey() = AppConstants.API_KEY
}