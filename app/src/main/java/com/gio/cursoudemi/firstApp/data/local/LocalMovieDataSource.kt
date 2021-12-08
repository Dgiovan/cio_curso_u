package com.gio.cursoudemi.firstApp.data.local

import android.util.Log
import com.gio.cursoudemi.application.AppConstants
import com.gio.cursoudemi.firstApp.data.model.Movie
import com.gio.cursoudemi.firstApp.data.model.MovieEntity
import com.gio.cursoudemi.firstApp.data.model.MovieList
import com.gio.cursoudemi.firstApp.data.model.toMovieList

class LocalMovieDataSource(private val movieDao: MovieDao) {

suspend fun getUpcomingMovies(): MovieList {
    Log.e("recueperando","yes")

    return movieDao.getAllMovies().filter { it.movie_type == "upcoming" }.toMovieList()
    }


    suspend fun getTopRatedMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "toprated" }.toMovieList()

    }


    suspend fun getPopularMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "popular" }.toMovieList()

    }

    suspend fun saveMovie(movie: MovieEntity){
       movieDao.saveMovie(movie)
    }
}
