package com.example.trabajopsp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trabajopsp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        acciones()
    }

    fun acciones(){
        binding.botonAceptar.setOnClickListener{
            if (!textoNombre.text.isEmpty()) {
                var intent: Intent = Intent(applicationContext, SecondActivity::class.java);
                var bundle: Bundle = Bundle();
                bundle.putString("nombre", textoNombre.text.toString())
                intent.putExtras(bundle);
                startActivity(intent)
                textoNombre.setText("")
            }
        }
    }
}