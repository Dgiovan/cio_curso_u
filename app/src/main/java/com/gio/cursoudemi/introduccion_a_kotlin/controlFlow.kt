package com.gio.cursoudemi.introduccion_a_kotlin

class controlFlow {

    val a = 1
    val b = 2
    //var max = a


    //if
   fun numeromayor(){
   /*    if(a<b) max =b

       print(max)*/
       var max : Int
       max = if(a>b){ a }else{ b }
       //como variable ternaria??
       var maximo = if(a>b) a else b
   }
    //when
    fun whenosich(){
        val x =2
        //when exaustivo
        when(x){
            1->{}
            2->{}
            3->{}
            else -> { }
        }

    }

    fun introduccionListas(){
        //se puede modificar
        var listamutable= mutableListOf("gio","angel","damian")
        listamutable.add("mayra")
        listamutable.removeAt(0)
        //no se puede modificar
        var listainmutable = listOf<String>("auto","moto","bicicleta")
        //ciclo for
        for (nombre in listainmutable){
            println(nombre)
        }
        ///ciclo for con indice y valor
        for ((index,value) in listainmutable.withIndex()){
            println("index $index y valor $value")
        }
        //foreach en una lista
        listainmutable.forEach {elementos ->
            println(elementos)
        }
        //for each con idice
        listainmutable.forEachIndexed { index, s ->
            println("lista $index y el valor $s")
        }
    }

    fun indroduccionWile(){
        var i = 1
        while(1<=5){
            println("valor de $i")
            i++
        }
        var sum  =0
        var  input: String
        do {
            println("Ingresa un numero")
            input = readLine()!!
            sum += input.toInt()
        }while (input!="0")

        println(sum)
    }

    //extension function
    fun String.removerPrimercaracter():String = this.substring(1,this.length -1)
}