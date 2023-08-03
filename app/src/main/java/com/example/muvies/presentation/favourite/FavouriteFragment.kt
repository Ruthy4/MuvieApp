package com.example.muvies.presentation.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.muvies.databinding.FragmentFavouriteBinding
import com.example.muvies.utils.Resource
import com.example.muvies.utils.hideView
import com.example.muvies.utils.showShortSnackBar
import com.example.muvies.utils.showView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment() {
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    private val favouriteMovieViewModel: FavouriteMovieViewModel by viewModels()
    private lateinit var favouriteMovieRecyclerAdapter: FavouriteMovieRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavouriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favouriteMovieViewModel.fetchFavMovieList()

        favouriteMovieRecyclerAdapter = FavouriteMovieRecyclerAdapter {
            val action = FavouriteFragmentDirections.actionFavouriteFragmentToMovieDetailsFragment(it)
            findNavController().navigate(action)
        }

        observeMovieFavResponse()
    }

    private fun observeMovieFavResponse() {
        favouriteMovieViewModel.favMovieListLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.progressIndicator.showView()
                }
                is Resource.Success -> {
                    binding.progressIndicator.hideView()
                    binding.favRecyclerView.adapter = favouriteMovieRecyclerAdapter
                    favouriteMovieRecyclerAdapter.submitList(resource.data)
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
