package com.gio.cursoudemi.firstApp.repository

import com.gio.cursoudemi.firstApp.data.model.MovieList

interface MovieRepository {
//suspend representa una corutina
    suspend fun getUpcomingMovies(): MovieList

    suspend fun getTopRatedMovies(): MovieList

    suspend fun getPopularMovies(): MovieList

}