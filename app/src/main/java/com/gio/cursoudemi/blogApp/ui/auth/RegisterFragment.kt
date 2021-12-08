package com.gio.cursoudemi.blogApp.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gio.cursoudemi.R
import com.gio.cursoudemi.blogApp.core.Resource
import com.gio.cursoudemi.blogApp.data.remote.auth.AuthDataSource
import com.gio.cursoudemi.blogApp.domain.auth.AuthRepoImpl
import com.gio.cursoudemi.databinding.FragmentRegisterBinding
import com.gio.cursoudemi.firstApp.presentation.auth.AuthModelFactory
import com.gio.cursoudemi.firstApp.presentation.auth.AuthViewModel
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private val  viewModel by viewModels<AuthViewModel> { AuthModelFactory(
        AuthRepoImpl(AuthDataSource())
    ) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        getSignUpInfo()
    }

    private fun getSignUpInfo(){


        binding.btnSignUp.setOnClickListener {

            val userName = binding.editUserName.text.toString().trim()
            val mail = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()
            validateFieldNotEmpty(arrayOf(binding.editUserName,binding.editTextEmail,binding.editTextPassword,binding.editTextConfirmPassword))
            if (password != confirmPassword)
            {
                binding.editTextConfirmPassword.error = "Password does not match"
                binding.editTextPassword.error = "Password does not match"
                return@setOnClickListener
            }

            createUser(mail,password,userName)
        }
    }

    private fun createUser(mail: String, password: String, userName: String) {
        viewModel.signUn(mail,password,userName).observe(viewLifecycleOwner, Observer {result ->
            when(result){
                is Resource.Loading ->{binding.btnSignUp.isEnabled =false}
                is Resource.Success ->{findNavController().navigate(R.id.action_registerFragment_to_setupProfileFragment)}
                is Resource.Failure ->{binding.btnSignUp.isEnabled =true
                    Toast.makeText(requireContext(), "Error : ${result.exepcion}", Toast.LENGTH_SHORT).show()
                    Log.e("Error","Error : ${result.exepcion}")
                }
            }

        })
    }


    private fun validateFieldNotEmpty(arrayOf: Array<TextInputEditText>): Boolean {
       arrayOf.forEach { field ->
           if (field.text.toString().isEmpty()){
               field.error = "this field is empty"
               Log.e("?","si")
               return false
           }
       }
        return true
    }
}