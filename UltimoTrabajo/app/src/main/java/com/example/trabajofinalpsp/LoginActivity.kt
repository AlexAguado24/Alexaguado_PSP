package com.example.trabajofinalpsp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.trabajofinalpsp.databinding.ActivityLoginBinding
import com.example.trabajofinalpsp.databinding.ActivityMainBinding
import com.example.trabajofinalpsp.objetos.User
import com.google.android.material.snackbar.Snackbar
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        butonActions()
    }

    private fun butonActions() {
        binding.botonAtras.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.botonAceptar.setOnClickListener {
            var passConvertido = metodohash(binding.editContrasenia.text.toString());
            var persona:User = (User(binding.editUsuario.text.toString(),passConvertido.toString()))
            //aqui traemos los datos del usuario
            var pref=getSharedPreferences(persona.nombre, Context.MODE_PRIVATE)
            var pass=pref.getString("pass","")

            //si el pass coincide
            if ((pass==persona.contrasenia) ){//tx_contr_s?.text.toString()
                Snackbar.make(binding.root,"Accediendo...", Snackbar.LENGTH_SHORT).show()
                //TODO ME FALTA DE ACÁ DIRECCIONARLA A LA 3RA ACTIVITY Y ME FALTA METER EL HASH COMO CONTRASEÑA
                val intent=Intent(this,PrincipalActivity::class.java)
                startActivity(intent)

            }else{
                Snackbar.make(binding.root,"Usuario o contraseña incorrectos.", Snackbar.LENGTH_SHORT).show()
            }
            binding.editUsuario.setText("")
            binding.editContrasenia.setText("")
        }
    }

    fun hexadecimal(resumen: ByteArray): String? {
        var hex = ""
        for (i in resumen.indices) {
            val h = Integer.toHexString(resumen[i].toInt() and 0xFF)
            if (h.length == 1) hex += "0"
            hex += h
        }
        return hex.uppercase(Locale.getDefault())
    }

    private fun metodohash(palabra: String): String? {
        val md: MessageDigest

        try {
            md = MessageDigest.getInstance("SHA512")
            val dataByte = palabra.toByteArray()
            md.update(dataByte)
            val resumen: ByteArray = md.digest()

            return hexadecimal(resumen)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }


}