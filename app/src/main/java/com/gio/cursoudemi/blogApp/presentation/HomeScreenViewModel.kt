package com.gio.cursoudemi.blogApp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.gio.cursoudemi.blogApp.core.Resource
import com.gio.cursoudemi.blogApp.data.models.Post
import com.gio.cursoudemi.blogApp.domain.home.homeScreenRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeScreenViewModel(private val repo : homeScreenRepo) : ViewModel() {

    fun fetchLatestPost() = liveData(viewModelScope.coroutineContext + Dispatchers.Main ){
            emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getLatestPost()).data)
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    val latestPost : StateFlow<Resource<List<Post>>> = flow {
        kotlin.runCatching {
            repo.getLatestPost()
        }
            .onSuccess {
                emit(it)}
            .onFailure { throwable ->
                emit(Resource.Failure(Exception(throwable)))
            }
    }.stateIn(
        scope = viewModelScope,
        started =  SharingStarted.WhileSubscribed(4000),
        initialValue = Resource.Loading()
    )


    fun registerLikeButtonState(postId: String, liked : Boolean)
    = liveData(viewModelScope.coroutineContext+Dispatchers.Main)
    {
        emit(Resource.Loading())
        kotlin.runCatching {
        repo.registerLikeButtonState(postId,liked)
        }.onSuccess {
         emit(Resource.Success(Unit))
        }.onFailure {
         emit(Resource.Failure(Exception(it.message)))
        }
    }

    /**Los state flow deben tener un valor por defecto inicializado*/
    //without Flow coroutine builder
    private val posts = MutableStateFlow<Resource<List<Post>>>(Resource.Loading())
    fun fetchPostsFromMutable() = viewModelScope.launch {
        kotlin.runCatching {
            repo.getLatestPost()
        }
            .onSuccess {
               posts.value  = it}
            .onFailure { throwable ->
               posts.value = Resource.Failure(Exception(throwable))
            }
    }

    fun getPost() = posts

   /* fun fetchLatestPost() = liveData(Dispatchers.IO){
        emit(Resource.Loading())

        kotlin.runCatching {
            repo.getLastestPost()
        }.onSuccess { flowList ->
            flowList.collect { emit(it)  }
        }.onFailure { throwable ->
            emit(Resource.Failure(Exception(throwable.message)))
        }
    }*/
}

class HomeScreenViewModelFactory(private val repo : homeScreenRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(homeScreenRepo::class.java).newInstance(repo)
    }

}