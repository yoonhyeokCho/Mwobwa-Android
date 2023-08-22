package com.bongku.mwobwa.ui.saved

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bongku.mwobwa.data.entity.SavedContentEntity
import com.bongku.mwobwa.databinding.ItemSavedBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class SavedRVAdapter(
    val context: Context,
    val dataSet: List<SavedContentEntity>
) : RecyclerView.Adapter<SavedRVAdapter.ViewHolder>() {

    interface ItemClick {
        fun onDeleteClick(savedContentEntity: SavedContentEntity)
    }

    var itemClick: ItemClick? = null

    inner class ViewHolder(val binding: ItemSavedBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.deleteText.setOnClickListener {
                itemClick?.onDeleteClick(dataSet[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSavedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.run {
            itemSavedCompany.text = dataSet[position].company
            itemSavedTitle.text = dataSet[position].title
            itemSavedScore.text = dataSet[position].score.toString() + "점"
            itemSavedCount.text = dataSet[position].count.toString() + "명"

            Glide.with(itemSavedImageView)
                .load("https://image.tmdb.org/t/p/w342${dataSet[position].poster_path}")
                .transform(CenterCrop())
                .into(itemSavedImageView)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}