package br.com.wsilva.tmdbexplorer.features.moviesearch

import android.os.Bundle
import br.com.wsilva.tmdbexplorer.R
import br.com.wsilva.tmdbexplorer.core.BasicActivity

class SearchMovieActivity: BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lay_search_movie_activity)
        //Configuração inicial
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?)
    {
        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentByTag(SearchMovieFragment.TAG)
        if (fragment == null) {
            fragment = SearchMovieFragment.newInstance()
        }
        if (savedInstanceState == null) {
            fragment.arguments = intent.extras
        } else {
            fragment.arguments = savedInstanceState
        }
        addFragmentToActivity(fragmentManager, fragment, R.id.frameLayout, SearchMovieFragment.TAG)
    }
}