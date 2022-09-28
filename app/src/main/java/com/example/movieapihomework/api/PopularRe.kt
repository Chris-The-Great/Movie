package com.example.movieapihomework.api

import com.example.movieapihomework.adapter.Adapter
import com.example.movieapihomework.databinding.MovieItemBinding
import com.example.movieapihomework.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface PopularRe {
    fun getPopularMoives() : Flow<UIState>
    fun getNewMoives() : Flow<UIState>
    fun getUpcomingMoives() : Flow<UIState>
    fun getMoivesDeat() : Flow<UIState>
}

class PopularREImpl @Inject constructor (
    private val popularApi: PopularApi
        ) : PopularRe {
    var movie : MoviesData? = null
    override fun getPopularMoives(): Flow<UIState> = flow {
        emit(UIState.LOADING)

        try {
            val response = popularApi.getPopMovies()
            if (response.isSuccessful){
                response.body()?.let {
                    emit(UIState.SUCCESS(it.MovieResults.mapToMoivesData()))
                } ?: throw NullResponseFromServer("Null")
            }
            else{
                throw FailureResponseFromServer(response.errorBody()?.string())
            }
        } catch (e :Exception){
            emit(UIState.ERROR(e))
        }
    }

    override fun getNewMoives(): Flow<UIState> = flow {
        emit(UIState.LOADING)

        try {
            val response = popularApi.getNewMovies()
            if (response.isSuccessful){
                response.body()?.let {
                    emit(UIState.SUCCESS2(it.MovieResults.mapToMoivesData2()))
                } ?: throw NullResponseFromServer("Null")
            }
            else{
                throw FailureResponseFromServer(response.errorBody()?.string())
            }
        } catch (e :Exception){
            emit(UIState.ERROR(e))
        }
    }

    override fun getUpcomingMoives(): Flow<UIState> = flow {

        try {
            val response = popularApi.getUpcomingMovies()
            if (response.isSuccessful){
                response.body()?.let {
                    emit(UIState.SUCCESS2(it.MovieResults.mapToMoivesData2()))
                } ?: throw NullResponseFromServer("Null")
            }
            else{
                throw FailureResponseFromServer(response.errorBody()?.string())
            }
        } catch (e :Exception){
            emit(UIState.ERROR(e))
        }
    }

    override fun getMoivesDeat():Flow<UIState> = flow {

        try {
            val response = popularApi.getMovieDeatils(id = Adapter().currentAMovie.toString())
            if (response.isSuccessful){
                response.body()?.let {
                    emit(UIState.SUCCESS3(it.mapToMoivesDeat()))
                } ?: throw NullResponseFromServer("Null")
            }
            else{
                throw FailureResponseFromServer(response.errorBody()?.string())
            }
        } catch (e :Exception){
            emit(UIState.ERROR(e))
        }
    }
}