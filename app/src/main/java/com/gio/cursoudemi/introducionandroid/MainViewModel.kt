package com.gio.cursoudemi.introducionandroid

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.parcel.Parcelize

class MainViewModel : ViewModel() {

    private val user = MutableLiveData<Usuario>(Usuario("gio",25))
    fun setUser(usuario: Usuario){
        user.value = usuario
    }
    fun getUsuario() : LiveData<Usuario>{
        return user
    }

}
@Parcelize
data class Usuario(val nombre: String, val edad : Int) : Parcelable