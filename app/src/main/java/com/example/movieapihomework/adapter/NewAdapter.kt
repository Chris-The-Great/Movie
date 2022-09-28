package com.example.movieapihomework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapihomework.R
import com.example.movieapihomework.databinding.MovieItemBinding
import com.example.movieapihomework.model.MoviesData
import com.example.movieapihomework.model.MoviesData2
import com.squareup.picasso.Picasso

class NewAdapter (
    private val movieDataNew :MutableList<MoviesData2> = mutableListOf()
) : RecyclerView.Adapter<NewAdapter.MovieViewHolder>(){


    fun updateNewMovies(newNew : List<MoviesData2>)
    {
        movieDataNew.clear()
        newNew.forEach{
            movieDataNew.add(it)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun getItemCount(): Int =
        movieDataNew.size



    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(movieDataNew[position])


    class MovieViewHolder(private val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(moviesData2: MoviesData2){
            binding.title.text = moviesData2.title
            binding.date.text = moviesData2.date

            Picasso.get()
                .load(moviesData2.poster)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_error)
                .into(binding.poster)
        }
    }
}