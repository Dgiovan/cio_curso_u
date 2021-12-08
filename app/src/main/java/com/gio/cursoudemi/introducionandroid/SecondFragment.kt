package com.gio.cursoudemi.introducionandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import com.gio.cursoudemi.R


class SecondFragment : Fragment(R.layout.fragment_second) {

   /* private var nombre : String? = ""
    private var edad   : Int?    = -1*/
    private val  viewModel : MainViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


  /*      nombre = requireArguments().getString(MI_NOMBRE)
        edad   = requireArguments().getInt(MI_EDAD)*/
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val txt  = view.findViewById<TextView>(R.id.saluda)
        val btn  = view.findViewById<Button>(R.id.btn)

        viewModel.getUsuario().observe(viewLifecycleOwner, Observer {
            txt.text = it.nombre +" "+ it.edad
        })

        btn.setOnClickListener{
            val result = "Resultado ola gio"
            setFragmentResult("request", bundleOf("bundlekey" to result))
        }
        //txt.text = "hola $nombre que tal los $edad"
    }
/*    companion object {
        private const val MI_NOMBRE  = "nombre"
        private const val MI_EDAD    = "edad"

        fun newInstance(nombre: String, edad : Int) = SecondFragment().apply {
            arguments = bundleOf(MI_NOMBRE to nombre, MI_EDAD to edad)
        }
    }*/
}