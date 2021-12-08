package com.gio.cursoudemi.navigation_componenets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gio.cursoudemi.R
import com.gio.cursoudemi.introducionandroid.MainViewModel
import com.gio.cursoudemi.introducionandroid.Usuario


class Home_Navigation : Fragment(R.layout.fragment_home__navigation) {

    // Este crea una instancia del view model para el fragment
    //private val viewModel : MainViewModel by viewModels()
    //esta crea una estancia para la activiti y los x fragmentos que contiene en este caso 2
    private lateinit var text :TextView
    private val viewModel : MainViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn = view.findViewById<Button>(R.id.navigate)
        text = view.findViewById(R.id.tv)
        btn.setOnClickListener {
         //   findNavController().navigate(R.id.action_home_Navigation_to_seconNavigation, bundleOf("nombre" to "Jesus","edad" to 26))
            viewModel.setUser(Usuario("giovanny",26))

     /*       val action = Home_NavigationDirections.actionHomeNavigationToSeconNavigation("gio",26)
            findNavController().navigate(action)*/
                    findNavController().navigate(R.id.action_home_Navigation_to_seconNavigation)
        }
        /**para obtener informacion de una accion de retorno*/
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Usuario>("user")?.observe(
                viewLifecycleOwner){result ->
            text?.text = result.nombre
        }
    }


}