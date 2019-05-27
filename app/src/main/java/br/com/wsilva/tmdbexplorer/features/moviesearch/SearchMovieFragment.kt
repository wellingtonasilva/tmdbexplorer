package br.com.wsilva.tmdbexplorer.features.moviesearch

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import br.com.wsilva.tmdbexplorer.R
import br.com.wsilva.tmdbexplorer.constants.AppConstants
import br.com.wsilva.tmdbexplorer.di.AppModule
import br.com.wsilva.tmdbexplorer.features.moviedetail.DetailMovieActivity
import br.com.wsilva.tmdbexplorer.features.moviesearch.di.DaggerSearchMovieComponent
import br.com.wsilva.tmdbexplorer.features.moviesearch.di.SearchMovieModule
import br.com.wsilva.tmdbexplorer.model.dto.SearchMovieGeneralDTO
import kotlinx.android.synthetic.main.lay_search_movie_fragment.*
import javax.inject.Inject

class SearchMovieFragment: Fragment(), SearchMovieContract.View, SearchMovieAdapter.SearchMovieAdapterListener {

    @Inject
    lateinit var presenter: SearchMoviePresenter

    companion object {
        val TAG: String = "SearchMovieFragment"
        fun newInstance(): SearchMovieFragment {
            return SearchMovieFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        DaggerSearchMovieComponent
            .builder()
            .appModule(AppModule())
            .searchMovieModule(SearchMovieModule(this))
            .build()
            .inject(this)
        setHasOptionsMenu(true)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_search_movie_fragment, container, false)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?)
    {
        inflater?.inflate(R.menu.menu_search, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean
            {
                searchItem?.collapseActionView()
                presenter.onQueryTextSubmit(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean
            {
                return false
            }
        })
    }

    override fun showMovie(list: List<SearchMovieGeneralDTO>) {
        val adapter = SearchMovieAdapter(activity!!, list, this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }

    override fun OnClickListener(movie: SearchMovieGeneralDTO) {
        showDetail(movie.id)
    }

    override fun showDetail(movieId: Int) {
        val intent = Intent(context, DetailMovieActivity::class.java)
        val bundle = Bundle()
        bundle.putInt(AppConstants.API_KEY, movieId)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun showMessage(value: Boolean) {
        txtMessage.visibility = if (value) View.VISIBLE else View.INVISIBLE
        recyclerView.visibility = if (value) View.INVISIBLE else View.VISIBLE
    }
}