package com.example.movieapihomework.model


import com.google.gson.annotations.SerializedName

data class PopularM(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val MovieResults: List<MovieResult?>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)