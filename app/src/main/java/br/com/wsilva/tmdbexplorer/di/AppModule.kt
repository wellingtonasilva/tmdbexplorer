package br.com.wsilva.tmdbexplorer.di

import br.com.wsilva.tmdbexplorer.constants.AppConstants
import br.com.wsilva.tmdbexplorer.util.AppUtils
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class AppModule {

    @Provides
    @Named("baseUrl")
    fun providesUrlSearhMovie() = AppConstants.BASE_URL

    @Provides
    @Named("apiKey")
    fun providesApiKey() = AppConstants.API_KEY

    @Provides
    fun providesRetrofit(@Named("baseUrl") url: String): Retrofit {
        return AppUtils.createRetrofit(url)
    }
}