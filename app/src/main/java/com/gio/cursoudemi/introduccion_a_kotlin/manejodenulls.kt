package com.gio.cursoudemi.introduccion_a_kotlin

class manejodenulls {

    init {
       // val nombre = "gio"
        //integrar el simbolo ? para decir que puede ser nulo
        val nombre:String? = null
        //uso del  Operador Elvis ?: si es nullo asigna el valor que espesifiquemos
        // EL operador Elvis nos sirve como una especie de operador ternario en donde la parte lógica
        // en vez de ser definida por nosotros valida que que no sea nulo ej:

        val c = nombre?.length  ?: "gio"
        //!!el programador asegura que no es nulo
        //? el sistema se asegura que puede ser null y hace que la app no crashe

        //tambien se puede hacer uso del operadopr let
        //este metodo leto solo ejecutara su instruccion si la variable no es nula
        nombre.let {
            print(it)
        }
        print(c)
    }
}