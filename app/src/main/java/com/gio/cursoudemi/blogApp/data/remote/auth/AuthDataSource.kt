package com.gio.cursoudemi.blogApp.data.remote.auth

import android.graphics.Bitmap
import android.net.Uri
import com.gio.cursoudemi.blogApp.data.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream

class AuthDataSource {

    suspend fun signIn(email : String ,password: String ) : FirebaseUser?{

        val authResult = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()

        return authResult.user
    }

    suspend fun signUn(email: String, password: String, username: String): FirebaseUser? {
        val authResult = FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
        authResult.user?.let {
            FirebaseFirestore.getInstance().collection("users").document(it.uid)
                .set(User(email,username)).await() }
        return authResult.user
    }

    suspend fun updateUserProfile(imageBitmap : Bitmap, username : String){
        val user = FirebaseAuth.getInstance().currentUser
        user?.uid?.let {
            val imageRef  =FirebaseStorage.getInstance().reference.child("${it}/profile_picture")
            val baos = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,baos)

            val downloadUrl = imageRef.putBytes(baos.toByteArray()).await().storage.downloadUrl.await().toString()

            val profileUpdate = UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .setPhotoUri(Uri.parse(downloadUrl))
                .build()

            user.updateProfile(profileUpdate).await()
        }

    }
}