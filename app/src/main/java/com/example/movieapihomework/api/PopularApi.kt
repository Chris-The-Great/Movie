package com.example.movieapihomework.api

import com.example.movieapihomework.model.MovieDeat
import com.example.movieapihomework.model.MoviesData
import com.example.movieapihomework.model.PopularM
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PopularApi {

    @GET("3/movie/popular?api_key=b50cae9f28439ad60dd4d456fb2bb7c4&language=en-US&page=1")
    suspend fun getPopMovies() : Response<PopularM>
    @GET("3/movie/now_playing?api_key=b50cae9f28439ad60dd4d456fb2bb7c4&language=en-US&page=1")
    suspend fun getNewMovies(): Response<PopularM>
    @GET("3/movie/upcoming?api_key=b50cae9f28439ad60dd4d456fb2bb7c4&language=en-US&page=1")
    suspend fun getUpcomingMovies() : Response<PopularM>

    @GET("https://api.themoviedb.org/3/movie/{movie_id}?api_key=b50cae9f28439ad60dd4d456fb2bb7c4&language=en-US")
    suspend fun getMovieDeatils(
        @Path("movie_id") id: String
    ) :Response<MovieDeat>

    companion object{
        const val BaseUrl= "https://api.themoviedb.org/"
    }

}