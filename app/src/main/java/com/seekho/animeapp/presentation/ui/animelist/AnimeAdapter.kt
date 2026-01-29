package com.seekho.animeapp.presentation.ui.animelist

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seekho.animeapp.R
import com.seekho.animeapp.databinding.ItemAnimeBinding
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
        val binding = ItemAnimeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(private val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(anime: Anime) {
            binding.txtTitle.text = anime.title
            binding.txtEpisodes.text = "Episodes: ${anime.episodes}"
            binding.txtRating.text = "Rating: ${anime.rating}"

            Glide.with(binding.root)
                .load(anime.posterUrl)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .into(binding.imgPoster)


            binding.root.setOnClickListener { onClick(anime) }
        }
    }
}



