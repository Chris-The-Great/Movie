package com.example.movieapihomework

import com.example.movieapihomework.model.MoviesData
import com.example.movieapihomework.model.MoviesDataDetails

sealed class ClickHandler{
    data class DetailsCall(val call : String): ClickHandler()
    data class DetailsClick(val moviesData: MoviesData?): ClickHandler()
}
