package com.gio.cursoudemi.blogApp.domain.camera

import android.graphics.Bitmap
import com.gio.cursoudemi.blogApp.data.remote.camera.CameraDataSource

class CameraRepositoryImpl(private val dataSource: CameraDataSource) : CameraRepository {

    override suspend fun uploadPhoto(imageBitmap: Bitmap, description: String) {
        dataSource.uploadPhoto(imageBitmap,description)
    }
}