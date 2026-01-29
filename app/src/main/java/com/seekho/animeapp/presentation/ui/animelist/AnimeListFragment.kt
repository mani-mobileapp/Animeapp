package com.seekho.animeapp.presentation.ui.animelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seekho.animeapp.AnimeApplication
import com.seekho.animeapp.R
import com.seekho.animeapp.databinding.FragmentAnimeListBinding
import com.seekho.animeapp.presentation.common.AnimeViewModelFactory
import kotlinx.coroutines.launch

class AnimeListFragment : Fragment() {

    private var _binding: FragmentAnimeListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AnimeListViewModel
    private lateinit var adapter: AnimeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val app = requireActivity().application as AnimeApplication

        viewModel = ViewModelProvider(
            this,
            AnimeViewModelFactory(app.container.repository)
        )[AnimeListViewModel::class.java]

        adapter = AnimeAdapter { anime ->
            val bundle = bundleOf("animeId" to anime.id)
            findNavController().navigate(
                R.id.animeDetailFragment,
                bundle
            )
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        observeData()
        setupPagination()

        viewModel.loadNextPage()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.animeList.collect { list ->
                        adapter.submitData(list)
                    }
                }

                launch {
                    viewModel.isLoading.collect { loading ->
                        binding.progressBar.visibility =
                            if (loading && adapter.itemCount == 0) View.VISIBLE else View.GONE
                    }
                }

                launch {
                    viewModel.isPaginating.collect { paginating ->
                        binding.bottomProgressBar.visibility =
                            if (paginating) View.VISIBLE else View.GONE
                    }
                }
            }
        }
    }

    private fun setupPagination() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                val lm = rv.layoutManager as LinearLayoutManager
                if (lm.findLastVisibleItemPosition() >= adapter.itemCount - 2) {
                    viewModel.loadNextPage()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

