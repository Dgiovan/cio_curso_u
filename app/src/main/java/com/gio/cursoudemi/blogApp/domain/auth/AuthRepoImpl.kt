package com.gio.cursoudemi.blogApp.domain.auth

import android.graphics.Bitmap
import com.gio.cursoudemi.blogApp.data.remote.auth.AuthDataSource
import com.google.firebase.auth.FirebaseUser

class AuthRepoImpl(private val dataSource: AuthDataSource) : AuthRepo {

    override suspend fun sinIn(email: String, password: String): FirebaseUser? =
        dataSource.signIn(email, password)

    override suspend fun sinUn(email: String, password: String, username: String): FirebaseUser? =
        dataSource.signUn(email, password, username)

    override suspend fun updateProfile(imageBitmap: Bitmap, username: String) = dataSource.updateUserProfile(imageBitmap, username)


}