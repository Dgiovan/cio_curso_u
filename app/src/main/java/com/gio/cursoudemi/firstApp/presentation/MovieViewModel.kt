package com.gio.cursoudemi.firstApp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.gio.cursoudemi.firstApp.core.Resource
import com.gio.cursoudemi.firstApp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MovieViewModel(private val repo : MovieRepository) : ViewModel() {

    //3 estados
    // carga cuando busca en el servidor
    // exito
    // fallo
    /**fun fetchUpComingMovies() = liveData(Dispatchers.IO){
        emit(Resource.Loading())

        try {
            emit(Resource.Succes(repo.getUpcomingMovies()))

        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }*/
//feach all or notingh
    fun fetchMainScreenMovies() = liveData(Dispatchers.IO){
        emit(Resource.Loading())

        try {
            emit(Resource.Succes(Triple(repo.getUpcomingMovies(),repo.getTopRatedMovies(),repo.getPopularMovies())))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}

//para crear mas llamadas
//data class NTuplen<T1,T2,T3,T4,T5>(val t1:T1,val t2:T2,val t3:T3,val t4:T4,val t5:T5)
class MovieModelFactory(private val repo : MovieRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}