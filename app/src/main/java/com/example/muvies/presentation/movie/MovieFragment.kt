package com.example.muvies.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.muvies.databinding.FragmentMovieBinding
import com.example.muvies.utils.Resource
import com.example.muvies.utils.hideView
import com.example.muvies.utils.showShortSnackBar
import com.example.muvies.utils.showView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var movieRecyclerAdapter: MovieRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieRecyclerAdapter = MovieRecyclerAdapter {
            val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment2(it)
            findNavController().navigate(action)
        }

        movieViewModel.movieListLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.progressIndicator.showView()
                }
                is Resource.Success -> {
                    binding.progressIndicator.hideView()
                    binding.movieRecyclerView.adapter = movieRecyclerAdapter
                    movieRecyclerAdapter.submitList(resource.data)
                }
                is Resource.Error -> {
                    binding.progressIndicator.hideView()
                    resource.error?.let { showShortSnackBar(it) }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
