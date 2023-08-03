package com.example.muvies.presentation.movieDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.muvies.data.local.dao.GenreData
import com.example.muvies.databinding.GenreRvItemBinding

class GenreRecyclerAdapter :
    ListAdapter<GenreData, GenreRecyclerAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(
        private val binding: GenreRvItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: GenreData) {
            binding.apply {
                genreTitleTv.text = genre.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GenreRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object DiffCallback : DiffUtil.ItemCallback<GenreData>() {
    override fun areItemsTheSame(oldItem: GenreData, newItem: GenreData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GenreData, newItem: GenreData): Boolean {
        return oldItem == newItem
    }
}
