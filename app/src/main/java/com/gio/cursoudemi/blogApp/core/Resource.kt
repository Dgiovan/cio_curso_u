package com.gio.cursoudemi.blogApp.core

import java.lang.Exception

sealed class Resource<out T> {

    //metodo de carga
    class Loading<out T> : Resource <T>()
    //metodo succes
    data class Success<out T>(val data: T): Resource<T>()
    //metodo error
    data class Failure(val exepcion: Exception): Resource<Nothing>()
}