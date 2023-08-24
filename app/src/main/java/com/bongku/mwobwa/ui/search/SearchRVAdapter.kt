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
import com.bongku.mwobwa.databinding.ItemSearchBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class SearchRVAdapter(
    val context: Context,
    val dataSet: List<ContentsResult>
) : RecyclerView.Adapter<SearchRVAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.run {
            itemSearchTitle.text = dataSet[position].title
            itemSearchScore.text = dataSet[position].voteAverage.toString() + "점"

            if (dataSet[position].poster_path != null) {
                Glide.with(itemSearchImageView)
                    .load("https://image.tmdb.org/t/p/w342${dataSet[position].poster_path}")
                    .transform(CenterCrop())
                    .into(itemSearchImageView)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
