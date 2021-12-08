package com.gio.cursoudemi.blogApp.data.remote.home

import com.gio.cursoudemi.blogApp.core.Resource
import com.gio.cursoudemi.blogApp.data.models.Post
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeScreenDataSource {

    /**con live data */
    suspend fun getLatestPost(): Resource<List<Post>>{

    val postList = mutableListOf<Post>()

    withContext(Dispatchers.IO){
        val querySnapshot = FirebaseFirestore.getInstance().collection("post").orderBy("created_at",
            Query.Direction.DESCENDING).get().await()

        for (post in querySnapshot.documents){
            post.toObject(Post::class.java)?.let { fbPost ->

                fbPost.apply { created_at  = post.getTimestamp("created_at",DocumentSnapshot.ServerTimestampBehavior.ESTIMATE)?.toDate() }
                postList.add(fbPost)
            }
        }
    }



    return Resource.Success(postList)
    }

    /**con flow*/
/*
    @ExperimentalCoroutinesApi
    suspend fun getLatestPost(): Flow<Resource<List<Post>>> = callbackFlow {

        val postList = mutableListOf<Post>()

        //crando referencia
        var postReference: Query? = null

        try {
            //dando valor a la referencia
            postReference = FirebaseFirestore.getInstance().collection("post").orderBy(
                "create_at",
                Query.Direction.DESCENDING
            )
        } catch (e: Throwable) {
            close(e)
            //solo se usa close cuando se hace uso de callbackFlow
        }


        val suscription = postReference?.addSnapshotListener { value, error ->

            if (value == null) return@addSnapshotListener

            try {
                postList.clear()
                for (post in value.documents) {
                    post.toObject(Post::class.java)?.let { fbPost ->

                        fbPost.apply {
                            created_at = post.getTimestamp(
                                "created_at",
                                DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                            )?.toDate()
                           *//* val liked = isPostLiked(iud,podtId)*//*
                        }
                        postList.add(fbPost)
                    }
                }

            } catch (e: Exception) {
                close(e)
            }


            offer(Resource.Success(postList))
        }
        //cerrar cuando se deje de escuchar

        awaitClose { suscription?.remove() }
    }*/


}