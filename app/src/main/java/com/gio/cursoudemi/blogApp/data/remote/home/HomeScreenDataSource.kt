package com.gio.cursoudemi.blogApp.data.remote.home

import android.util.Log
import com.gio.cursoudemi.blogApp.core.Resource
import com.gio.cursoudemi.blogApp.data.models.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class HomeScreenDataSource {

    /**con live data */
/**    suspend fun getLatestPost(): Result<List<Post>> {

        val postList = mutableListOf<Post>()

        withContext(Dispatchers.IO) {
            val querySnapshot = FirebaseFirestore.getInstance().collection("post")
                .orderBy("created_at", Query.Direction.DESCENDING).get().await()

            for (post in querySnapshot.documents) {
                post.toObject(Post::class.java)?.let { fbPost ->

                    val isLiked = FirebaseAuth.getInstance().currentUser?.let { safeUser ->
                        isPostLiked(post.id, safeUser.uid)
                    }

                    fbPost.apply {
                        created_at = post.getTimestamp(
                            "created_at",
                            DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                        )?.toDate()
                        id = post.id

                        if(isLiked != null) {
                            liked = isLiked
                        }
                    }

                    postList.add(fbPost)
                }
            }
        }
        return Resource.Success(postList)
    }*/
    suspend fun getLatestPost(): Resource<List<Post>>{

    val postList = mutableListOf<Post>()

    withContext(Dispatchers.IO){
        val querySnapshot = FirebaseFirestore.getInstance().collection("post")
            .orderBy("created_at", Query.Direction.DESCENDING).get().await()

        for (post in querySnapshot.documents){
            post.toObject(Post::class.java)?.let { fbPost ->

                val isLiked = FirebaseAuth.getInstance().currentUser?.let { safeUser->
                    isPostLiked(post.id,safeUser.uid)
                }

                fbPost.apply {
                    created_at  = post.getTimestamp(
                        "created_at",DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                    )?.toDate()
                    id = post.id

                    if (isLiked != null){
                        liked = isLiked
                    }
                }
                postList.add(fbPost)
            }

        }
    }

    return Resource.Success(postList)
    }

    private suspend fun isPostLiked(postId: String, uid: String): Boolean {
        val post = FirebaseFirestore.getInstance().collection("postsLikes").document(postId).get().await()
        if(!post.exists()) {
            return false
        }
        val likeArray: List<String> = post.get("likes") as List<String>
        return likeArray.contains(uid)
    }
    fun registerLikeButtonState(postId: String, liked: Boolean) {

        val increment = FieldValue.increment(1)
        val decrement = FieldValue.increment(-1)
        val database = FirebaseFirestore.getInstance()
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val postRef = database.collection("post").document(postId)

        val postsLikesRef = database.collection("postsLikes").document(postId)




        database.runTransaction { transaction ->
            val snapshot = transaction.get(postRef)
            val likeCount = snapshot.getLong("likes")
            if (likeCount != null) {
                if (likeCount >= 0) {
                    if (liked) {
                        if (transaction.get(postsLikesRef).exists()) {
                            transaction.update(postsLikesRef, "likes", FieldValue.arrayUnion(uid))
                        } else {
                            transaction.set(
                                postsLikesRef,
                                hashMapOf("likes" to arrayListOf(uid)),
                                SetOptions.merge()
                            )
                        }

                        transaction.update(postRef, "likes", increment)
                    } else {
                        transaction.update(postRef, "likes", decrement)
                        transaction.update(postsLikesRef, "likes", FieldValue.arrayRemove(uid))
                    }
                }
            }
        }.addOnFailureListener {
            Resource.Failure( Exception(it.message))
        }
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