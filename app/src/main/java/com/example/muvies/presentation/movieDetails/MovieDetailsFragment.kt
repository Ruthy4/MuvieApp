package com.example.muvies.presentation.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.muvies.R
import com.example.muvies.data.local.MovieData
import com.example.muvies.databinding.FragmentMovieDetailsBinding
import com.example.muvies.utils.Constants.poster_url
import com.example.muvies.utils.Resource
import com.example.muvies.utils.showShortSnackBar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private val movieDetailViewModel: MovieDetailViewModel by viewModels()
    private lateinit var genreRecyclerAdapter: GenreRecyclerAdapter
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieData = args.movieData

        movieData.id?.let { fetchMovieDetail(movieId = it) }
        movieDetailViewModel.checkIfFavorite(movieData)

        with(binding) {
            detailsTitleTv.text = movieData.title
            synopsisContentTv.text = movieData.overview
            movieDetailsIv.load(poster_url + movieData.backdrop_path)
            releaseDateTv.text = movieData.release_date
            detailsMovieRatingTv.text = getString(R.string.rating, movieData.vote_average.toString())

            navigateBackArrow.setOnClickListener { findNavController().popBackStack() }
        }
        observeDetailResponse()

        binding.favouriteIv.setOnClickListener {
            addFavouriteMovie(args.movieData)
        }
        observeFavoriteMovie()
    }

    private fun fetchMovieDetail(movieId: Int) {
        movieDetailViewModel.fetchMovieDetail(movieId)
    }

    private fun observeDetailResponse() {
        movieDetailViewModel.movieDetailLiveData.observe(viewLifecycleOwner) { resource ->
            genreRecyclerAdapter = GenreRecyclerAdapter()

            when (resource) {
                is Resource.Loading -> {
                    Timber.d("Loading")
                }
                is Resource.Success -> {
                    val movieDetail = resource.data
                    with(binding) {
                        binding.genreRv.adapter = genreRecyclerAdapter
                        genreRecyclerAdapter.submitList(movieDetail?.genres)
                        detailsDurationTv.text = getString(R.string.runtime, movieDetail?.runtime)
                    }
                }
                is Resource.Error -> {
                    resource.error?.let { showShortSnackBar(it) }
                }
            }
        }
    }

    private fun observeFavoriteMovie() {
        val isMovieFav = movieDetailViewModel.isFavorite.value
        if (isMovieFav == true) {
            binding.favouriteIv.setImageResource(R.drawable.favorite_filled)
        } else {
            binding.favouriteIv.setImageResource(R.drawable.favorite_border)
        }
    }
    private fun addFavouriteMovie(movieData: MovieData) {
        val isMovieFavorite = movieDetailViewModel.checkIfFavorite(movieData)
        if (!isMovieFavorite) {
            val movie = movieData.copy(isFavorite = true)
            binding.favouriteIv.setImageResource(R.drawable.favorite_filled)
            movieDetailViewModel.checkIfFavoriteOrNot(movie, true)
        } else {
            val movie = movieData.copy(isFavorite = false)
            movieDetailViewModel.checkIfFavoriteOrNot(movie, false)
            binding.favouriteIv.setImageResource(R.drawable.favorite_border)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
