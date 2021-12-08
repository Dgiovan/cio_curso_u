package com.gio.cursoudemi.blogApp.domain.camera

import android.graphics.Bitmap

interface CameraRepository {

    suspend fun uploadPhoto(imageBitmap : Bitmap,description : String)
}