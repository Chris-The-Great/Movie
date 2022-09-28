package com.example.movieapihomework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapihomework.R
import com.example.movieapihomework.databinding.MovieDItemBinding
import com.example.movieapihomework.databinding.MovieItemBinding
import com.example.movieapihomework.model.MoviesData
import com.example.movieapihomework.model.MoviesData2
import com.example.movieapihomework.model.MoviesDataDetails
import com.squareup.picasso.Picasso

class DetailsAdapter (
    private val movieDetails :MutableList<MoviesDataDetails> = mutableListOf()
) : RecyclerView.Adapter<DetailsAdapter.MovieViewHolder>(){

    fun updateDetailsMovies(newDeats: MoviesDataDetails){
        movieDetails.clear()
        movieDetails.add(newDeats)
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            MovieDItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun getItemCount(): Int =
        movieDetails.size



    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(movieDetails[position])


    class MovieViewHolder(private val binding: MovieDItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(moviesDe: MoviesDataDetails){

            binding.name.text = moviesDe.title
            binding.descrption.text = moviesDe.description
            binding.duration.text = moviesDe.duration.toString()

            Picasso.get()
                .load(moviesDe.poster)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_error)
                .into(binding.poster)

            binding.root.setOnClickListener{

            }
        }
    }
}