package com.example.movieapihomework.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapihomework.R
import com.example.movieapihomework.databinding.MovieDItemBinding
import com.example.movieapihomework.databinding.MovieItemBinding
import com.example.movieapihomework.model.MovieResult
import com.example.movieapihomework.model.MoviesData
import com.example.movieapihomework.model.MoviesData2
import com.example.movieapihomework.model.PopularM
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates

class Adapter (
    private val movieData :MutableList<MoviesData> = mutableListOf()
) : RecyclerView.Adapter<Adapter.MovieViewHolder>(){

    var currentAMovie : Int = 0

    fun updatePopMovies(newPop : List<MoviesData>){
        movieData.clear()
        newPop.forEach{
            movieData.add(it)
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
        movieData.size



    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(movieData[position])


    class MovieViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root){
        var currentMovie :String? = null

        fun bind(moviesData: MoviesData){
            binding.title.text = moviesData.title
            binding.date.text = moviesData.date
            binding.pop.text = moviesData.pop.toString()

            Picasso.get()
                .load(moviesData.poster)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_error)
                .into(binding.poster)

            binding.root.setOnClickListener{
                binding.root.findNavController().navigate(R.id.action_popularFragment_to_detailsFragment)
                currentMovie = moviesData.id.toString()
                Adapter().currentAMovie = moviesData.id
                Log.d("Data",moviesData.id.toString() )
                Log.d("Data",Adapter().currentAMovie.toString() )
            }
        }
    }
}