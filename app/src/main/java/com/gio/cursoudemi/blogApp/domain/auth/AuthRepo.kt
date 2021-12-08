package com.gio.cursoudemi.blogApp.domain.auth

import android.graphics.Bitmap
import com.google.firebase.auth.FirebaseUser

interface AuthRepo {

    suspend fun sinIn(email: String,password: String) : FirebaseUser?
    suspend fun sinUn(email: String, password: String, username: String): FirebaseUser?
    suspend fun updateProfile(imageBitmap: Bitmap,username: String)
}