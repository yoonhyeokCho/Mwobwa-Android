package com.bongku.mwobwa.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bongku.mwobwa.R
import com.bongku.mwobwa.data.entity.ContentsResult
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class SearchRVAdapter(
    val context: Context,
    val dataSet: List<ContentsResult>
) : RecyclerView.Adapter<SearchRVAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.item_search_title)
        val vote_score = view.findViewById<TextView>(R.id.item_search_score)
        val poster = view.findViewById<ImageView>(R.id.item_search_image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.run {
            title.text = dataSet[position].title
            vote_score.text = dataSet[position].voteAverage.toString() + "점"

            if (dataSet[position].poster_path != null) {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w342${dataSet[position].poster_path}")
                    .transform(CenterCrop())
                    .into(poster)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
