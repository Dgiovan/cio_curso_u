package com.gio.cursoudemi.blogApp.presentation.camera

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.gio.cursoudemi.blogApp.core.Resource
import com.gio.cursoudemi.blogApp.domain.camera.CameraRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class CameraViewModel(private val repo : CameraRepository) : ViewModel()  {

    fun uploadPhoto(imageBitmap : Bitmap, description: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
        emit(Resource.Success(repo.uploadPhoto(imageBitmap,description)) )
        }catch (e : Exception){
         emit(Resource.Failure(e))
        }
    }

}
class CameraFactoryViewModel(private val repo : CameraRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CameraViewModel(repo) as T
    }

}