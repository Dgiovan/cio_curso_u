package com.gio.cursoudemi.introducionandroid

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import com.gio.cursoudemi.R


class Homefragment : Fragment(R.layout.fragment_homefragment) {

    // Este crea una instancia del view model para el fragment
    //private val viewModel : MainViewModel by viewModels()
    //esta crea una estancia para la activiti y los x fragmentos que contiene en este caso 2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.butonnavigation)
        val txt    = view.findViewById<TextView>(R.id.saludo)
        setFragmentResultListener("request"){
            key,bundle ->
            val result = bundle.getString("bundlekey")
            txt.text  = result
        }

        button.setOnClickListener {

          /*  requireActivity().supportFragmentManager.commit {
                replace(R.id.containerview,SecondFragment.newInstance("gio",25))
                addToBackStack("fragmentHome")//asigna un id ára póder navegar a el puede ser null
            }*/
        }
    }
}