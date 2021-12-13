package com.gio.cursoudemi.blogApp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gio.cursoudemi.R
import com.gio.cursoudemi.blogApp.core.Resource
import com.gio.cursoudemi.blogApp.data.models.Post
import com.gio.cursoudemi.blogApp.data.remote.home.HomeScreenDataSource
import com.gio.cursoudemi.blogApp.domain.home.homeScreenRepoImpl
import com.gio.cursoudemi.blogApp.presentation.HomeScreenViewModel
import com.gio.cursoudemi.blogApp.presentation.HomeScreenViewModelFactory
import com.gio.cursoudemi.blogApp.ui.home.adapter.HomeScreenAdapter
import com.gio.cursoudemi.blogApp.ui.home.adapter.OnPostClickListener
import com.gio.cursoudemi.databinding.FragmentHomeScreenBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class homeScreenFragment : Fragment(R.layout.fragment_home_screen) , OnPostClickListener {

    private lateinit var binding : FragmentHomeScreenBinding
    private val viewModel by viewModels<HomeScreenViewModel> {
        HomeScreenViewModelFactory(homeScreenRepoImpl(HomeScreenDataSource()))
    }
/*
    private val viewmodel
*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeScreenBinding.bind(view)

/*    viewModel.fetchPostsFromMutable()
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.latestPost.collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        Log.e("resource", "cargando")
                    }
                    is Resource.Success -> {
                        if (result.data.isEmpty()) {
                            Log.e("resource", "${result.data}")
                            Toast.makeText(requireContext(), "No hay contenido", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Log.e("resource", "${result.data}")
                            binding.rvHome.adapter =
                                HomeScreenAdapter(result.data, this@homeScreenFragment)
                        }


                    }
                    is Resource.Failure -> {
                        Log.e("resource", "fallo ${result.exepcion}")
                    }
                }
            }
        }
    }*/
     viewModel.fetchLatestPost().observe(viewLifecycleOwner, Observer {result->
        when (result) {
            is Resource.Loading -> {
                Log.e("resource", "cargando")
            }
            is Resource.Success -> {
                if(result.data.isEmpty()){
                    Log.e("resource","${result.data}")
                    Toast.makeText(requireContext(), "No hay contenido", Toast.LENGTH_SHORT).show()
                }else{
                    Log.e("resource","${result.data}")
                    binding.rvHome.adapter = HomeScreenAdapter(result.data,this)
                }


            }
            is Resource.Failure -> {
                Log.e("resource", "fallo ${result.exepcion}")
            }
        }
    })


  /*  viewModel.fetchLatestPost().observe(viewLifecycleOwner, Observer { result ->
        when (result) {
            is Resource.Loading -> {
                Log.e("resource", "cargando")
            }
            is Resource.Succes -> {

                binding.rvHome.adapter = HomeScreenAdapter(result.data)

            }
            is Resource.Failure -> {
                Log.e("resource", "fallo ${result.exepcion}")
            }
        }
    })*/



}

    override fun onLikedButtonClick(post: Post, liked: Boolean) {
        viewModel.registerLikeButtonState(post.id,liked).observe(viewLifecycleOwner)
        {
            when(it){
                is Resource.Loading -> {}
                is Resource.Success -> {}
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "${it.exepcion}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}