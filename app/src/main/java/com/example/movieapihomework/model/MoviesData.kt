package com.example.movieapihomework.model

import android.icu.text.CaseMap.Title
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data class for data needed for popualr movies
 */
@Parcelize
data class MoviesData (
    val title :String = "Invalid",
    val poster :String = "Invalid",
    val date : String = "Invalid",
    val pop : Double = 999999.99,
    val id : Int = 999999
) : Parcelable

/**
 * data class for data needed for new and upcoming movies
 */
data class MoviesData2(
    val title: String = "Invalid",
    val poster : String = "Invalid",
    val date : String = "Invalid"
)

/**
 * data class for data needed for details fragment
 */
@Parcelize
data class MoviesDataDetails(
    val title: String = "Invalid",
    val poster : String = "Invalid",
    val description : String = "Invalid",
    //val genres : String = "Invalid",
    val duration : Int = 999999,
    //val website : String = "Invalid"
) : Parcelable

fun List<MovieResult?>?.mapToMoivesData() : List<MoviesData> =
    this?.map {
        MoviesData(
            title = it?.originalTitle ?: "Invalid",
            poster = it?.posterPath ?: "Invalid",
            date = it?.releaseDate ?: "Invalid",
            pop = it?.popularity ?: 999999.99,
            id = it?.id?: 999999
        )
    } ?: emptyList()


fun List<MovieResult?>?.mapToMoivesData2() : List<MoviesData2> =
    this?.map {
        MoviesData2(
            title = it?.originalTitle ?: "Invalid",
            poster = it?.posterPath ?: "Invalid",
            date = it?.releaseDate ?: "Invalid",
        )
    } ?: emptyList()

fun List<MovieDeat?>?.mapToMoivesDeat(): List<MoviesDataDetails> =
    this?.map {
        MoviesDataDetails(
            title = it?.title ?:"Invalid",
            poster = it?.posterPath?:"Invalid",
            description = it?.overview?: "Invalid",
            //genres = it?.genres?: "Invalid",
            duration = it?.runtime?: 999999,
            //website = it?.homepage "Invalid"
        )
    }?: emptyList()



fun MovieResult.mapToMovies():MoviesData = MoviesData(
    title = this.originalTitle ?: "Invalid",
    poster = this.posterPath ?: "Invalid",
    date = this.releaseDate ?: "Invalid",
    pop = this.popularity ?: 999999.99,
    id = this.id ?: 999999
)

fun MovieResult.mapToMovies2():MoviesData2 = MoviesData2(
    title = this.originalTitle ?: "Invalid",
    poster = this.posterPath ?: "Invalid",
    date = this.releaseDate ?: "Invalid",
)

fun MovieDeat.mapToMoivesDeat():MoviesDataDetails =
        MoviesDataDetails(
            title = this.title ?:"Invalid",
            poster = this.posterPath?:"Invalid",
            description = this.overview?: "Invalid",
            //genres = it?.genres?: "Invalid",
            duration = this.runtime?: 999999,
            //website = it?.homepage "Invalid"
        )
