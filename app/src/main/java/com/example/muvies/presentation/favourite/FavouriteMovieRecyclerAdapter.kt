package com.example.muvies.presentation.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.muvies.data.local.MovieData
import com.example.muvies.databinding.FavouriteRvItemBinding
import com.example.muvies.utils.Constants.poster_url

class FavouriteMovieRecyclerAdapter(private val onItemClick: (MovieData) -> Unit) :
    ListAdapter<MovieData, FavouriteMovieRecyclerAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(
        private val binding: FavouriteRvItemBinding,
        private val onItemClick: (MovieData) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movieData: MovieData) {
            binding.apply {
                favouriteMovieTitleTv.text = movieData.title
                favouriteIv.load(poster_url + movieData.backdrop_path)
                favouriteMovieReleaseDateTv.text = movieData.release_date
                favRatingText.text = movieData.vote_average.toString()

                favRootLayout.setOnClickListener {
                    onItemClick.invoke(movieData)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FavouriteRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object DiffCallback : DiffUtil.ItemCallback<MovieData>() {
    override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem == newItem
    }
}
