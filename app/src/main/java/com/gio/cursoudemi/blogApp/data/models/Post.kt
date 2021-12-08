package com.gio.cursoudemi.blogApp.data.models

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp
import java.util.*


data class Post(
                @ServerTimestamp
                var created_at: Date? =null,
                val post_image: String = "",
                val post_description : String = "",
                val poster: Poster? = null,
                val likes : Long = 0,
                @Exclude @JvmField
                val id: String="",
                @Exclude @JvmField
                var liked: Boolean=false)

data class Poster(val profile_picture: String = "", val userName: String ? = "",val uid : String? = null)
//exclude evita mapear el objeto con ese atributo y los 2 atributos juntos @Exclude @JvmField ayudan a manejar de manera local dicho parametro
