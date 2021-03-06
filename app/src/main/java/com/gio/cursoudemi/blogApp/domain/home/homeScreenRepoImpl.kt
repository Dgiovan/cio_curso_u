package com.gio.cursoudemi.blogApp.domain.home

import com.gio.cursoudemi.blogApp.core.Resource
import com.gio.cursoudemi.blogApp.data.models.Post
import com.gio.cursoudemi.blogApp.data.remote.home.HomeScreenDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class homeScreenRepoImpl(private val dataSource: HomeScreenDataSource) : homeScreenRepo {

   // @ExperimentalCoroutinesApi
    override suspend fun getLatestPost(): Resource<List<Post>> = dataSource.getLatestPost()
    override suspend fun registerLikeButtonState(postId: String, liked: Boolean) = dataSource.registerLikeButtonState(postId, liked)
}