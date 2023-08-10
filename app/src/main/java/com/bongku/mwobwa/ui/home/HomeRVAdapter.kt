package com.bongku.mwobwa.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.bongku.mwobwa.R
import com.bongku.mwobwa.data.entity.ContentsResult

class HomeRVAdapter(
    val context: Context,
    val dataSet: List<ContentsResult>
) : RecyclerView.Adapter<HomeRVAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val plus_img = view.findViewById<ImageView>(R.id.item_plus)
        val ggim = view.findViewById<TextView>(R.id.item_ggimText)
        val story = view.findViewById<TextView>(R.id.item_story)
        val company = view.findViewById<TextView>(R.id.item_company_text)
        val title = view.findViewById<TextView>(R.id.item_title)
        val vote_score = view.findViewById<TextView>(R.id.item_vote_score)
        val vote_count = view.findViewById<TextView>(R.id.item_vote_count)
        val spinner = view.findViewById<Spinner>(R.id.item_genre_spinner)

        init {
            initSpinner(spinner)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.run {
            title.text = dataSet[position].title
            vote_score.text = "평점 : " + dataSet[position].voteAverage.toString()
            vote_count.text = "참가자 : " + dataSet[position].voteCount.toString()
            story.text = dataSet[position].overview
            title.text = dataSet[position].title
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    private fun initSpinner(spinner: Spinner) {
        ArrayAdapter.createFromResource(
            context,
            R.array.spinner_genre_array,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = it
        }
    }
}
