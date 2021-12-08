package com.gio.cursoudemi.navigation_componenets

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gio.cursoudemi.R
import com.gio.cursoudemi.databinding.FragmentSeconNavigationBinding
import com.gio.cursoudemi.introducionandroid.MainViewModel
import com.gio.cursoudemi.introducionandroid.SecondFragment
import com.gio.cursoudemi.introducionandroid.Usuario


class SeconNavigation : Fragment(R.layout.fragment_secon_navigation) {

  /*  private var saludo = ""
    val args : SeconNavigationArgs by navArgs()*/
  private val viewModel : MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
  /*      arguments?.let { bundle ->
            saludo = "hola que tal ${bundle.getString(MI_NOMBRE)} tienes ${bundle.getInt(MI_EDAD)}"
        }*/
/*
        saludo = args.nombreg!!
*/

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bin = FragmentSeconNavigationBinding.bind(view)
        viewModel.getUsuario().observe(viewLifecycleOwner, Observer {user->
            bin.textss.text = user.nombre + "${user.edad}"
        })
        var btn= view.findViewById<Button>(R.id.navigate)
        btn.setOnClickListener {
            findNavController().navigate(Uri.parse("curso://detail"))
        }

        /**Para regresar informacion programaticamente con navigation components*/
        findNavController().previousBackStackEntry?.savedStateHandle?.set("user",Usuario("Mayra",25))
    }
/*    companion object {
        private const val MI_NOMBRE  = "nombre"
        private const val MI_EDAD    = "edad"

        fun newInstance(nombre: String, edad : Int) = SeconNavigation().apply {
            arguments = bundleOf(MI_NOMBRE to nombre, MI_EDAD to edad)
        }
    }*/
}