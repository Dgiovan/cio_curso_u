package com.gio.cursoudemi.blogApp.ui.camera

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gio.cursoudemi.R
import com.gio.cursoudemi.blogApp.core.Resource
import com.gio.cursoudemi.blogApp.data.remote.auth.AuthDataSource
import com.gio.cursoudemi.blogApp.data.remote.camera.CameraDataSource
import com.gio.cursoudemi.blogApp.domain.auth.AuthRepoImpl
import com.gio.cursoudemi.blogApp.domain.camera.CameraRepositoryImpl
import com.gio.cursoudemi.blogApp.presentation.camera.CameraFactoryViewModel
import com.gio.cursoudemi.blogApp.presentation.camera.CameraViewModel
import com.gio.cursoudemi.databinding.FragmentCameraBinding
import com.gio.cursoudemi.firstApp.presentation.auth.AuthModelFactory
import com.gio.cursoudemi.firstApp.presentation.auth.AuthViewModel


class CameraFragment : Fragment(R.layout.fragment_camera) {

    private lateinit var binding : FragmentCameraBinding
    private var bitmap : Bitmap? = null
    private val  viewModel by viewModels<CameraViewModel> { CameraFactoryViewModel(
        CameraRepositoryImpl(CameraDataSource())
    ) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCameraBinding.bind(view)
        val takePictureIntent  = Intent(MediaStore.ACTION_IMAGE_CAPTURE)


        try {
            takePhotoResult.launch(takePictureIntent)
        }catch (e : ActivityNotFoundException){
            Toast.makeText(requireContext(), "No se encontro aplicacion para abrir la camara", Toast.LENGTH_SHORT).show()
        }

        binding.uploadPhoto.setOnClickListener {
            bitmap?.let {
                viewModel.uploadPhoto(it, binding.edtxPhotoDecription.text.toString().trim())
                    .observe(viewLifecycleOwner,
                        Observer { result ->
                            when (result) {
                                is Resource.Loading -> {
                                }
                                is Resource.Success -> {
                                    findNavController().popBackStack()
                                }
                                is Resource.Failure -> {
                                }
                            }
                        })
            }

        }
    }

    var takePhotoResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        requestPhoto ->
        if (requestPhoto.resultCode == Activity.RESULT_OK){
            Log.e("logResult","${requestPhoto.data}")
             bitmap = requestPhoto.data?.extras?.get("data") as Bitmap
            binding.imageAddPhoto.setImageBitmap(bitmap)
        }
    }
}