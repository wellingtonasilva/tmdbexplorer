package br.com.wsilva.tmdbexplorer.features.moviesearch

import br.com.wsilva.tmdbexplorer.service.BaseApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchMoviePresenter: SearchMovieContract.Presenter {

    private val view: SearchMovieContract.View
    private val baseApiService: BaseApiService
    private val bag = CompositeDisposable()

    @Inject
    constructor(view: SearchMovieContract.View, baseApiService: BaseApiService) {
        this.view = view
        this.baseApiService = baseApiService
    }

    override fun onQueryTextSubmit(query: String) {
        bag.add(baseApiService
            .searchMovie(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {result -> view.showMessage(false); view.showMovie(result.results)})
    }

    override fun onDestroy() {
        bag.clear()
    }
}