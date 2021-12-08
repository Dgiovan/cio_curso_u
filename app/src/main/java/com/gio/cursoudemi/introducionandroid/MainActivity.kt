package com.gio.cursoudemi.introducionandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.gio.cursoudemi.R

class MainActivity : AppCompatActivity() {
    /**introducion
     * instalacion , crear primer proyecto, estructura del proyeccto,emulador,tipos de layouts
     * actividad extiende app copmpac activity */

    /**ciclo de vida de la aplicacion primera ves creada
     * on create
     * on start
     * on resume
     * on pause
     * on stop

     * cuando esta en foregraund
     * on restart
     * on start
     * on resume*/

     /** on destroi cuando la aplicacion muere*/

    //declaracion
    lateinit var title : TextView
    override fun onStart() {
        super.onStart()
        Log.e("lifecycle","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("lifecycle","onresume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("lifecycle","onpause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("lifecycle","onstop")
    }

    override fun onDestroy() {
        super.onDestroy()
        //cuando salimos del de la aplicacion
        Log.e("lifecycle","onDestroy")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("lifecycle","oncreate")
        //casteo o inicializacion
        title = findViewById(R.id.principal_title)
        val boton = findViewById<Button>(R.id.button_example)
        var numero = 25
        //lamda
        boton.setOnClickListener{
            newMetodResults()
            //title.text = "Hola primer texto cambiado desde un boton $numero"
        }


  /*      var variables = Introduccion()

        variables.variables()
        //intancia de un objeto
        val persona = Introduccion.Persona("giovanny","corona")
        persona.saludar()

        val persona2 = Introduccion.Persona()
        persona2.nombre  ="Dgio"
        persona2.apellido="Dev"
        persona2.saludar()

        var gio = Introduccion.User("Dgio",25)
        println(gio.toString())

        println(Introduccion.DIAS.MARTES.numero)

        println(variables.ispair(4))*/
    }

    private fun navigateTo(){
        val intent = Intent(this, SecondActivty2::class.java)
        intent.putExtra("saludo","Hola gio")
        startActivity(intent)
    }
/*    private fun getResults(){
        val intent = Intent(this,SecondActivty2::class.java)
        intent.putExtra("saludo","Hola gio get Results")
        startActivityForResult(intent,1)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode ==1){
            if (resultCode == Activity.RESULT_OK){
                title.text = data?.getStringExtra("gio")
            }
        }
    }*/



    private fun newMetodResults(){
        val intent = Intent(this, SecondActivty2::class.java)
        intent.putExtra("saludo","Hola gio get Results")
        getResult.launch(intent)
    }

    var getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
        result ->
        if (result.resultCode == Activity.RESULT_OK){
            val data: Intent? = result.data
            title.text = "hoLA DESDE NUEVO METODO ${data?.getStringExtra("gio")}"
        }
    }
}