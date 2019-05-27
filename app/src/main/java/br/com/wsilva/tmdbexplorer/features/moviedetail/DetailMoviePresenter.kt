package br.com.wsilva.tmdbexplorer.features.moviedetail

import br.com.wsilva.tmdbexplorer.service.BaseApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailMoviePresenter: DetailMovieContract.Presenter {
    private val view: DetailMovieContract.View
    private val baseApiService: BaseApiService
    private val bag = CompositeDisposable()
    var movieId: Int = 0

    @Inject
    constructor(view: DetailMovieContract.View, baseApiService: BaseApiService) {
        this.view = view
        this.baseApiService = baseApiService
    }

    override fun loadDetailMovie(movieId: Int) {
        bag.add(baseApiService
            .getMovieDetails(movieId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { result -> view.showDetailMovie(result) })
    }

    override fun onDestroy() {
        bag.clear()
    }
}