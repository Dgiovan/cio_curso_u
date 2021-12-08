package com.gio.cursoudemi.blogApp

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.gio.cursoudemi.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.imaginativeworld.whynotimagecarousel.utils.setImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.*

class BlogMain : AppCompatActivity() {
    private lateinit var image: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_main)
        val btn = findViewById<Button>(R.id.take_photo)
        image = findViewById(R.id.image_photo)
        btn.setOnClickListener { dispatchTakePictureIntent() }
 /*       val db = FirebaseFirestore.getInstance()
            //Consultar informacion
        *//**Consulta en tiempo real*//*
            db.collection("ciudades").document("La").addSnapshotListener{value,error->
                value?.let {
                    document->
                    val ciudad = document.toObject(ciudades::class.java)
                    Log.e("TAG", " data color : ${ciudad?.color}")
                    Log.e("TAG"," data population : ${ciudad?.population}")
                }
            }*/
   /**Consultas a la base de datos
    *  .collection("ciudades")
    *
            .document("La").get()
            .addOnSuccessListener { document ->
               document.let {
                   val ciudad = document.toObject(ciudades::class.java)
                   Log.e("TAG", " data color : ${ciudad?.color}")
                   Log.e("TAG"," data population : ${ciudad?.population}")
                   val color = document.getString("color")
                   val population = document.getLong("population")
                   Log.e("TAG","Document data : ${document.data}")
                   Log.e("TAG"," data color : $color")
                   Log.e("TAG"," data population : $population")

               }

            }.addOnFailureListener { exception ->
                    Log.d("TAG", "get failed with ", exception)

                }*/
        //colocar informacion
  /*      db.collection("tienda").document("quesadillas").set(quesadillas("queso con pollo",14)).addOnSuccessListener{
            Log.e("TAG","se guardo correctamente")

        }.addOnFailureListener{
            Log.e("TAG","No se guardo")
        }*/
    }

    private fun dispatchTakePictureIntent(){
        //levanta la camara
    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            registerForResults.launch(takePictureIntent)
        }catch (e : ActivityNotFoundException){
            Toast.makeText(this, "No se encontro aplicacion para tomar fotos", Toast.LENGTH_SHORT).show()
        }
    }

    private var registerForResults = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
        if (result.resultCode == Activity.RESULT_OK){
            val imageBitmap = result?.data?.extras?.get("data") as Bitmap
            image.setImageBitmap(imageBitmap)
            uploadPicture(imageBitmap)
        }
    }

    private fun uploadPicture(bitmap : Bitmap){
        val storeangeRef = FirebaseStorage.getInstance().reference

       // val imageRef = storeangeRef.child("image.jpg")
        /**Nombre aleatorio*/
        //val imageRef = storeangeRef.child("${UUID.randomUUID()}.jpg")

        /**Nombre aleatorio y carpeta*/
        val imageRef = storeangeRef.child("images/${UUID.randomUUID()}.jpg")
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos)
        val data = baos.toByteArray()

        val uploadTask = imageRef.putBytes(data)

        uploadTask.continueWithTask {task ->
            if (!task.isSuccessful){
                task.exception?.let {
                    throw it}
            }
            imageRef.downloadUrl

        }.addOnCompleteListener{task->
            if(task.isSuccessful){
                val downloadUri = task.result.toString()
                FirebaseFirestore.getInstance().collection("ciudades").document("La").update(mapOf("image" to downloadUri))
                Log.e("storange","UploadPictureURL $downloadUri")
            }
        }
    }
}

data class quesadillas(val name : String= "queso",val price: Int=14)
data class ciudades(val color : String="black",val population :  Int=0)