package com.example.movieapihomework.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapihomework.ClickHandler
import com.example.movieapihomework.R
import com.example.movieapihomework.api.PopularRe
import com.example.movieapihomework.databinding.MovieDItemBinding
import com.example.movieapihomework.databinding.MovieItemBinding
import com.example.movieapihomework.model.*
import com.example.movieapihomework.ui.MoviesViewModel
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates

class Adapter (
    private val movieData :MutableList<MoviesData> = mutableListOf(),
    private val  clickHandler: (ClickHandler) -> Unit
) : RecyclerView.Adapter<Adapter.MovieViewHolder>(){

    companion object {
        var currentAMovie: String = ""
    }

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
        holder.bind(movieData[position],clickHandler)


    class MovieViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root){
        var currentMovie :String? = null



        fun bind(moviesData: MoviesData, clickHandler: (ClickHandler) -> Unit){
            binding.title.text = moviesData.title
            binding.date.text = moviesData.date
            binding.pop.text = moviesData.pop.toString()

            Picasso.get()
                .load(moviesData.poster)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_error)
                .into(binding.poster)

            binding.root.setOnClickListener{
                currentMovie = moviesData.id.toString()
                currentAMovie = moviesData.id.toString()
                clickHandler(ClickHandler.DetailsClick(moviesData))

                Log.d("Data",moviesData.id.toString() )
                Log.d("Data",currentAMovie.toString() )
            }
        }
    }
}