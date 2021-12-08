package com.gio.cursoudemi.blogApp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gio.cursoudemi.R
import com.gio.cursoudemi.blogApp.core.Resource
import com.gio.cursoudemi.blogApp.data.remote.auth.AuthDataSource
import com.gio.cursoudemi.blogApp.domain.auth.AuthRepoImpl
import com.gio.cursoudemi.databinding.FragmentLogingBinding
import com.gio.cursoudemi.firstApp.presentation.auth.AuthModelFactory
import com.gio.cursoudemi.firstApp.presentation.auth.AuthViewModel
import com.google.firebase.auth.FirebaseAuth


class Loging : Fragment(R.layout.fragment_loging) {


    private lateinit var binding : FragmentLogingBinding
    private val firebaseAuth by lazy { FirebaseAuth.getInstance()}
    private val  viewModel by viewModels<AuthViewModel> { AuthModelFactory(
            AuthRepoImpl(AuthDataSource())
    ) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLogingBinding.bind(view)

        isUserLoggedIn()
        doLogin()
        goToSingUpPage()
    }

    private fun isUserLoggedIn(){
        firebaseAuth.currentUser?.let { user ->
            if (user.displayName.isNullOrEmpty()){
                findNavController().navigate(R.id.action_loging_to_setupProfileFragment)
            }else{
                findNavController().navigate(R.id.action_loging_to_homeScreenFragment)
            }

        }
    }
    private fun doLogin(){
        binding.btnSignig.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            validateCredentials(email,password)
            signIn(email,password)
        }
    }

    private fun  goToSingUpPage(){
        binding.txtSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loging_to_registerFragment)
        }

    }

    private fun validateCredentials(email : String,password : String){
        if (email.isEmpty()){
            binding.editTextEmail.error = "E-mail is empty"
            return
        }
        if (password.isEmpty()){
            binding.editTextPassword.error = "Password is empty"
            return
        }
    }
    private fun signIn(email : String,password : String){
        viewModel.signIn(email, password).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnSignig.isEnabled = false
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    if (result.data?.displayName.isNullOrEmpty()){
                        findNavController().navigate(R.id.action_loging_to_setupProfileFragment)
                    }else{
                        findNavController().navigate(R.id.action_loging_to_homeScreenFragment)
                    }
                }
                is Resource.Failure ->{
                    binding.progressBar.visibility = View.GONE
                    binding.btnSignig.isEnabled = true
                    Toast.makeText(requireContext(), "Error ${result.exepcion} ", Toast.LENGTH_SHORT).show()
                }
           
            }
        })
    }
}