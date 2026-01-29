package com.seekho.animeapp.presentation.ui.animedetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.seekho.animeapp.AnimeApplication
import com.seekho.animeapp.R
import com.seekho.animeapp.databinding.FragmentAnimeDetailBinding
import com.seekho.animeapp.domain.model.Anime
import com.seekho.animeapp.presentation.common.AnimeViewModelFactory
import com.seekho.animeapp.util.NetworkResult
import kotlinx.coroutines.launch

class AnimeDetailFragment : Fragment() {

    private var _binding: FragmentAnimeDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AnimeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val app = requireActivity().application as AnimeApplication

        viewModel = ViewModelProvider(
            this,
            AnimeViewModelFactory(app.container.repository)
        )[AnimeDetailViewModel::class.java]

        val animeId = requireArguments().getInt("animeId")

        viewModel.loadAnimeDetail(animeId)

        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.anime.collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.contentLayout.visibility = View.GONE
                        binding.errorText.visibility = View.GONE
                    }

                    is NetworkResult.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.contentLayout.visibility = View.VISIBLE
                        binding.errorText.visibility = View.GONE
                        renderUi(result.data)
                    }

                    is NetworkResult.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.contentLayout.visibility = View.GONE
                        binding.errorText.visibility = View.VISIBLE
                        binding.errorText.text = result.message
                    }
                }
            }
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun renderUi(anime: Anime) {
        binding.textTitle.text = anime.title
        binding.textSynopsis.text = anime.synopsis
        binding.textEpisodes.text = getString(R.string.episodes, anime.episodes)
        binding.textRating.text = getString(R.string.rating, anime.rating)

        if (!anime.trailerUrl.isNullOrEmpty()) {
            binding.webView.settings.javaScriptEnabled = true
            binding.webView.loadUrl(anime.trailerUrl)
            binding.webView.visibility = View.VISIBLE
            binding.poster.visibility = View.GONE
        } else {
            binding.webView.visibility = View.GONE
            binding.poster.visibility = View.VISIBLE
            Glide.with(this)
                .load(anime.posterUrl)
                .into(binding.poster)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


