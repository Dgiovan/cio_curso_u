package com.gio.cursoudemi.blogApp.domain.home

import com.gio.cursoudemi.blogApp.core.Resource
import com.gio.cursoudemi.blogApp.data.models.Post
import kotlinx.coroutines.flow.Flow


interface   homeScreenRepo {

    /*suspend fun getLastestPost(): Flow<Resource<List<Post>>>*/
    suspend fun getLatestPost(): Resource<List<Post>>
    suspend fun registerLikeButtonState(postId : String, liked : Boolean)

}