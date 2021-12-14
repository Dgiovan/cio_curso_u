package com.gio.cursoudemi.blogApp.data.remote.camera

import android.graphics.Bitmap
import com.gio.cursoudemi.blogApp.data.models.Post
import com.gio.cursoudemi.blogApp.data.models.Poster
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.util.*

class CameraDataSource {

    suspend fun uploadPhoto(imageBitmap : Bitmap,description : String){
        val user = FirebaseAuth.getInstance().currentUser
        user?.uid?.let {
            val randomName = UUID.randomUUID().toString()
            val imageRef  = FirebaseStorage.getInstance().reference.child("${it}/posts/${randomName}")

            val baos = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,baos)
            var downloadUrl = ""
            withContext(Dispatchers.IO) {
                downloadUrl = imageRef.putBytes(baos.toByteArray()).await().storage.downloadUrl.await().toString()
            }
           user.let {userInLet ->
               userInLet.displayName?.let { displayName ->
                   FirebaseFirestore
                       .getInstance()
                       .collection("post")
                       .add(Post(poster = Poster(userName = displayName,uid = user.uid,profile_picture = userInLet.photoUrl.toString()) ,
                                 post_image = downloadUrl,
                                 post_description = description,
                                 likes = 0))

               }
           }
        }
    }
}