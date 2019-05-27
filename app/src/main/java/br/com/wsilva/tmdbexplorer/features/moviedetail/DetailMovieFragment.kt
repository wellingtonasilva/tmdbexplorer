package br.com.wsilva.tmdbexplorer.features.moviedetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.tmdbexplorer.R
import br.com.wsilva.tmdbexplorer.constants.AppConstants
import br.com.wsilva.tmdbexplorer.di.AppModule
import br.com.wsilva.tmdbexplorer.features.moviedetail.di.DaggerDetailMovieComponent
import br.com.wsilva.tmdbexplorer.features.moviedetail.di.DetailMovieModule
import br.com.wsilva.tmdbexplorer.model.dto.SearchMovieGeneralDTO
import br.com.wsilva.tmdbexplorer.util.AppUtils.Companion.convertToHourMinute
import br.com.wsilva.tmdbexplorer.util.AppUtils.Companion.formatNumber
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lay_detail_movie_fragment.*
import javax.inject.Inject

class DetailMovieFragment: Fragment(), DetailMovieContract.View {

    @Inject
    lateinit var presenter: DetailMoviePresenter

    companion object {
        val TAG: String = "DetailMovieFragment"
        fun newInstance(): DetailMovieFragment {
            return DetailMovieFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //DI
        DaggerDetailMovieComponent
            .builder()
            .appModule(AppModule())
            .detailMovieModule(DetailMovieModule(this))
            .build()
            .inject(this)

        //Parameter
        if (savedInstanceState == null) {
            val bundle = arguments
            if (bundle != null) {
                presenter.movieId = bundle.getInt(AppConstants.API_KEY)
            }
        } else {
            presenter.movieId = savedInstanceState.getInt(AppConstants.API_KEY)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.loadDetailMovie(presenter.movieId)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(AppConstants.API_KEY, presenter.movieId)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_detail_movie_fragment, container, false)
        return view
    }

    override fun showDetailMovie(movie: SearchMovieGeneralDTO) {
        Picasso.get()
            .load(AppConstants.BASE_URL_IMAGE_ORIGINAL + movie.backdrop_path)
            .into(imgBackdrop)

        txtTitle.text = movie.title
        txtOverview.text = movie.overview
        txtRuntime.text = convertToHourMinute(movie.runtime)
        txtAppOriginalLanguage.text = movie.original_language
        txtBudget.text = formatNumber(movie.budget)
        txtRevenue.text = formatNumber(movie.revenue)
    }
}