package com.seekho.animeapp.presentation.ui.animelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seekho.animeapp.R
import com.seekho.animeapp.domain.model.Anime

class AnimeAdapter(
    private val onClick: (Anime) -> Unit
) : RecyclerView.Adapter<AnimeAdapter.ViewHolder>() {

    private val list = mutableListOf<Anime>()

    fun submitData(newList: List<Anime>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_anime, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.txtTitle)
        private val poster = view.findViewById<ImageView>(R.id.imgPoster)

        fun bind(anime: Anime) {
            title.text = anime.title
            Glide.with(itemView)
                .load(anime.posterUrl)
                .into(poster)

            itemView.setOnClickListener { onClick(anime) }
        }
    }
}


