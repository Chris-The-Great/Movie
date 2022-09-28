package com.example.movieapihomework.model

sealed class UIState {
    object LOADING: UIState()
    data class SUCCESS(val popular: List<MoviesData>) : UIState()
    data class SUCCESS2(val newUpcoming: List<MoviesData2>) : UIState()
    data class SUCCESS3(val deat : MoviesDataDetails) : UIState()
    data class ERROR(val error : Exception) : UIState()
}