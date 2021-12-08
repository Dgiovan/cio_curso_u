package com.gio.cursoudemi.blogApp.ui.auth

import android.app.Activity
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gio.cursoudemi.R
import com.gio.cursoudemi.blogApp.core.Resource
import com.gio.cursoudemi.blogApp.data.remote.auth.AuthDataSource
import com.gio.cursoudemi.blogApp.domain.auth.AuthRepoImpl
import com.gio.cursoudemi.databinding.FragmentSetupProfileBinding
import com.gio.cursoudemi.firstApp.presentation.auth.AuthModelFactory
import com.gio.cursoudemi.firstApp.presentation.auth.AuthViewModel


class SetupProfileFragment : Fragment(R.layout.fragment_setup_profile) {

    private lateinit var binding: FragmentSetupProfileBinding
    private var bitmap : Bitmap? = null
    private val viewModel by  viewModels<AuthViewModel> { AuthModelFactory(
        AuthRepoImpl(AuthDataSource())
    ) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSetupProfileBinding.bind(view)

        binding.profileImage.setOnClickListener {
            val takePictureIntent  = Intent(MediaStore.ACTION_IMAGE_CAPTURE)


            try {
                takePhotoResult.launch(takePictureIntent)
            }catch (e : ActivityNotFoundException){
                Toast.makeText(requireContext(), "No se encontro aplicacion para abrir la camara", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCreateProfile.setOnClickListener {
            val username = binding.editUsername.text.toString().trim()
            if (bitmap != null && username.isNotEmpty()){
                val alertDialog = AlertDialog.Builder(requireContext()).setMessage("Uploading Photo ...").create()
                viewModel.updateUserProfile(bitmap!!,username).observe(viewLifecycleOwner, Observer {
                    when(it){
                        is Resource.Loading ->{alertDialog.show()}
                        is Resource.Success ->{alertDialog.dismiss()
                        findNavController().navigate(R.id.action_setupProfileFragment_to_homeScreenFragment)}
                        is Resource.Failure ->{alertDialog.dismiss()}
                    }
                })
            }
            /*bitmap?.let {
                viewModel.updateUserProfile(it,username)
            }*/

        }

    }
    var takePhotoResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            requestPhoto ->
        if (requestPhoto.resultCode == Activity.RESULT_OK){
            Log.e("logResult","${requestPhoto.data}")
             bitmap = requestPhoto.data?.extras?.get("data") as Bitmap
            binding.profileImage.setImageBitmap(bitmap)
        }
    }
}