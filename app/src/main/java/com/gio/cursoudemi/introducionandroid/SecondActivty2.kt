package com.gio.cursoudemi.introducionandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.gio.cursoudemi.R

class SecondActivty2 : AppCompatActivity() {
    private var saludo = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_activty2)
        intent.extras?.let { bundle ->
            saludo = bundle.getString("saludo") ?: ""
        }
        val txt = findViewById<TextView>(R.id.anyText)
        val btn = findViewById<Button>(R.id.backtohome)

        txt.text  = saludo
        btn.setOnClickListener{
            var resultold = Intent();
            resultold.putExtra("gio","desde actividad results")
            setResult(Activity.RESULT_OK,resultold)
            finish()
        }
    }
    private fun navigateTo(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}