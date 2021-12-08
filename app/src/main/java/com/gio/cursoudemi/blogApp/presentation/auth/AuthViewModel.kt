package com.gio.cursoudemi.firstApp.presentation.auth

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.gio.cursoudemi.blogApp.core.Resource
import com.gio.cursoudemi.blogApp.domain.auth.AuthRepo
import kotlinx.coroutines.Dispatchers

class AuthViewModel(private val repo : AuthRepo): ViewModel() {

    fun signIn(email : String , password :String) = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
        emit(Resource.Success(repo.sinIn(email, password)))
        }catch (e : Exception){
            emit(Resource.Failure(e))
        }
    }

    fun signUn(email : String , password :String,username : String) = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.sinUn(email, password,username)))
        }catch (e : Exception){
            emit(Resource.Failure(e))
        }
    }

    fun updateUserProfile(bitmapProfile : Bitmap,userName : String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.updateProfile(bitmapProfile,userName)))
        }catch (e : Exception){
            emit(Resource.Failure(e))
        }
    }
}

class AuthModelFactory(private val repo : AuthRepo) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repo) as T
    }

}