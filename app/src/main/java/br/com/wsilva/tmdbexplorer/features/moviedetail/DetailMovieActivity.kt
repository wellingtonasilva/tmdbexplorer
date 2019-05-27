package br.com.wsilva.tmdbexplorer.features.moviedetail

import android.os.Bundle
import br.com.wsilva.tmdbexplorer.R
import br.com.wsilva.tmdbexplorer.core.BasicActivity

class DetailMovieActivity: BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lay_detail_movie_activity)
        //Configuração inicial
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?)
    {
        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentByTag(DetailMovieFragment.TAG)
        if (fragment == null) {
            fragment = DetailMovieFragment.newInstance()
        }
        if (savedInstanceState == null) {
            fragment.arguments = intent.extras
        } else {
            fragment.arguments = savedInstanceState
        }
        addFragmentToActivity(fragmentManager, fragment, R.id.frameLayout, DetailMovieFragment.TAG)
    }
}