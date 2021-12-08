package com.gio.cursoudemi.blogApp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.gio.cursoudemi.R
import com.gio.cursoudemi.blogApp.core.hide
import com.gio.cursoudemi.blogApp.core.show
import com.gio.cursoudemi.databinding.ActivityBlogMainBinding
import com.gio.cursoudemi.databinding.ActivityMain2Binding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMain2Binding
    private lateinit var  navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment2) as NavHostFragment
        navController = navHostFragment.navController
      /** parapoder usar esta navegacion se puede cambiar el contenedor del id nav_host_fragment a un fragment
        binding.bottomNavigationView.setupWithNavController(findNavController(R.id.nav_host_fragemnt))*/
        binding.bottomNavigationView.setupWithNavController(navController)

        observeDestinationChange()
    }

    private fun observeDestinationChange(){
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.loging -> { binding.bottomNavigationView.hide()}
                R.id.registerFragment ->{ binding.bottomNavigationView.hide() }
                R.id.setupProfileFragment ->{binding.bottomNavigationView.hide()}
                else ->{ binding.bottomNavigationView.show()}
            }
        }
    }

}