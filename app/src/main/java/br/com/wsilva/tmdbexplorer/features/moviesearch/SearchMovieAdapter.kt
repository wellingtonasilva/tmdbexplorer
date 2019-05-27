package br.com.wsilva.tmdbexplorer.features.moviesearch

import android.content.Context
import android.graphics.Color
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.tmdbexplorer.R
import br.com.wsilva.tmdbexplorer.constants.AppConstants
import br.com.wsilva.tmdbexplorer.model.dto.SearchMovieGeneralDTO
import br.com.wsilva.tmdbexplorer.util.AppUtils
import br.com.wsilva.tmdbexplorer.util.AppUtils.Companion.getYearOfDate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lay_search_movie_adapter.view.*
import java.time.LocalDate
import java.util.*

class SearchMovieAdapter(private val context: Context,
                         private val list: List<SearchMovieGeneralDTO>,
                         private val listener: SearchMovieAdapterListener) : RecyclerView.Adapter<SearchMovieAdapter.ViewHolder>()
{
    interface SearchMovieAdapterListener {
        fun OnClickListener(movie: SearchMovieGeneralDTO)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        var view = LayoutInflater.from(context).inflate(R.layout.lay_search_movie_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val entity = list[position]
        val releaseDate = AppUtils.convertToDate(entity.releaseDate)
        holder.txtTitle.text = entity.title
        //TODO Remove this code. Its not good do this work here.
        holder.txtYear.text = getYearOfDate(releaseDate).toString()
        holder.txtYear.setTextColor(if (getYearOfDate(releaseDate) == getYearOfDate(Date())) Color.RED else Color.BLACK)

        Picasso.get()
            .load(AppConstants.BASE_URL_IMAGE_W500 + entity.posterPath)
            .into(holder.imgPoster)
        holder.card_view.setOnClickListener { listener.OnClickListener(entity) }
    }

    override fun getItemCount(): Int
    {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val card_view = itemView.card_view
        val imgPoster = itemView.imgPoster
        val txtTitle = itemView.txtTitle
        val txtYear = itemView.txtYear
    }
}