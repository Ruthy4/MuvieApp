package com.example.muvies.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.muvies.R
import com.example.muvies.data.local.MovieData
import com.example.muvies.databinding.MovieRvItemBinding
import com.example.muvies.utils.Constants.poster_url

class MovieRecyclerAdapter(private val onItemClick: (MovieData) -> Unit) :
    ListAdapter<MovieData, MovieRecyclerAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(
        private val binding: MovieRvItemBinding,
        private val onItemClick: (MovieData) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movieData: MovieData) {
            binding.apply {
                val yearString = movieData.release_date?.substring(0, 4)
                movieTitleTv.apply {
                    text = context.getString(R.string.movie_title, movieData.title, yearString?.toInt())
                }
                movieImage.load(poster_url + movieData.backdrop_path)

                rootLayout.setOnClickListener {
                    onItemClick.invoke(movieData)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
