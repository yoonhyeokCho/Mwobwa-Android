package com.bongku.mwobwa.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bongku.mwobwa.R
import com.bongku.mwobwa.data.entity.ContentsResult
import com.bongku.mwobwa.databinding.ItemHomeBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class HomeRVAdapter(
    val context: Context,
    val dataSet: List<ContentsResult>
) : RecyclerView.Adapter<HomeRVAdapter.ViewHolder>() {

    interface ItemClick {
        fun onGgimClick(contentsEntity: ContentsResult)
    }

    var itemClick: ItemClick? = null

    inner class ViewHolder(val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.run {
                itemPlus.setOnClickListener {
                    itemClick?.onGgimClick(dataSet[adapterPosition])
                }
                itemGgimText.setOnClickListener {
                    itemClick?.onGgimClick(dataSet[adapterPosition])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.run {
            itemTitle.text = dataSet[position].title
            itemVoteScore.text = "평점 : " + dataSet[position].voteAverage.toString()
            itemVoteCount.text = "참가자 : " + dataSet[position].voteCount.toString()
            itemStory.text = dataSet[position].overview
            itemTitle.text = dataSet[position].title
            itemCompanyText.text = dataSet[position].company

            val contentsGenre = genreIdToStr(dataSet[position].genreIDS)
            itemGenreText.text = "장르 : " + contentsGenre

            Glide.with(itemImageView)
                .load("https://image.tmdb.org/t/p/w342${dataSet[position].poster_path}")
                .transform(CenterCrop())
                .into(itemImageView)
        }
    }

    private fun genreIdToStr(ids: List<Long>): String {
        Log.d("genre", "genreIdToStr: ${ids}")
        val genreMapping = mapOf(
            28L to "Action",
            12L to "Adventure",
            16L to "Animation",
            35L to "Comedy",
            80L to "Crime",
            99L to "Documentary",
            18L to "Drama",
            10751L to "Family",
            14L to "Fantasy",
            36L to "History",
            27L to "Horror",
            10402L to "Music",
            9648L to "Mystery",
            10749L to "Romance",
            878L to "Science Fiction",
            10770L to "TV Movie",
            53L to "Thriller",
            10752L to "War",
            37L to "Western",
            10759L to "Action & Adventure",
            10762L to "Kids",
            10763L to "News",
            10764L to "Reality",
            10765L to "Sci-Fi & Fantasy",
            10766L to "Soap",
            10767L to "Talk",
            10768L to "War & Politics"
        )

        val mappedGenre = ids.mapNotNull {
            genreMapping[it]
        }

        return mappedGenre.joinToString(", ")
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
