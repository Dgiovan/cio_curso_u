package com.gio.cursoudemi.firstApp.data.remote

import com.gio.cursoudemi.application.AppConstants
import com.gio.cursoudemi.firstApp.data.model.MovieList
import com.gio.cursoudemi.firstApp.repository.webService

class RemoteMovieDataSource(private val webService: webService) {

    suspend fun getUpcomingMovies():MovieList = webService.getUpcomingMovies(AppConstants.API_KEY)

    suspend fun getTopRatedMovies():MovieList = webService.getTopRatedMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies():MovieList  = webService.getPopularMovies(AppConstants.API_KEY)
}