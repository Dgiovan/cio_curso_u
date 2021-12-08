package com.gio.cursoudemi.introduccion_a_kotlin

import java.lang.IllegalArgumentException

const val direction ="1 san rafael"
class Introduccion {




    fun variables(){
        //Java
        //int a = 2;
        //int b = 3;
        //int c = a+b;
        //final double iva = 0.16
        //kotlin tiene una inferencia de datos
        var a : Int = 2 //tipo mutable
        var b       = 3
        var c       = a+b

        println("Suma $c ")

        val iva     = .016 //tipo inmutable "constante"
    }

    class constantes{
        //companion object puede representar algo similar a public static final en java
        companion object{
            const val number = 1
        }
    }
    //clases
    //clase con constructor y constructor vacio
    class Persona( var nombre: String = "" ,  var apellido : String = ""){


        fun saludar(){
            println("Bienvenido $nombre $apellido")
        }
    }

    //data clases
    //clases para almacenar informacion ejemplo un objeto de tipo ´persona
    //al hacer un data clas podemos acceder direcatamente a los metodos toString, copy, equals ,hashcode
    data class User(var nombre:String ,var edad: Int)

    enum class DIAS(val numero:Int){
        LUNES(1),
        MARTES(2),
        MIERCOLES(3),
        JUEVES(4),
        VIERNES(5),
        SABADO(6),
        DOMINGO(7)
    }

    //funciones
    fun ispair(numero:Int):Boolean
    {
        return numero % 2 == 0
    }

    /** genericos*/

    fun main(){
     Element(/*"gio"*/gio("jesus",26))
        PairElement(/*"gio"*/gio("jesus",26),25)
    }
    data class gio(val nombre: String,val edad :Int)
    class Element<T>(value : T? =null){
        init{
            if (value==null) throw IllegalArgumentException("the value is null")
            println("el valor es $value")
        }
    }

    class PairElement<T,R>(iElement : T,fElement : R){
        init{
            println("el valor es $iElement y elemento 2 $fElement")
        }
    }
}