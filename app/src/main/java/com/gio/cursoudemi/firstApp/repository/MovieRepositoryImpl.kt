package com.gio.cursoudemi.firstApp.repository

import android.util.Log
import com.gio.cursoudemi.firstApp.core.InternetCheck
import com.gio.cursoudemi.firstApp.data.local.LocalMovieDataSource
import com.gio.cursoudemi.firstApp.data.model.MovieList
import com.gio.cursoudemi.firstApp.data.model.toMovieEntity
import com.gio.cursoudemi.firstApp.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(private val dataSource: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {

    override suspend fun getUpcomingMovies():MovieList {
        return if (InternetCheck.isNetworkAvailable()){
            dataSource.getUpcomingMovies().results.forEach {movie->
                dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
            }
          dataSourceLocal.getUpcomingMovies()
        }else{
            dataSourceLocal.getUpcomingMovies()
        }

    }

    override suspend fun getTopRatedMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()){
            dataSource.getTopRatedMovies().results.forEach {movie->
            dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
        }
            dataSourceLocal.getTopRatedMovies()}else{dataSourceLocal.getTopRatedMovies()}

    }

    override suspend fun getPopularMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()){
            dataSource.getPopularMovies().results.forEach {movie->
                dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
            }
            dataSourceLocal.getPopularMovies()

            }else{ dataSourceLocal.getPopularMovies()  }
    }

}

/*    override suspend fun getTopRatedMovies(): MovieList = dataSource.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovies()*/
