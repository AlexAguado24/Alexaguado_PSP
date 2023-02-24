package com.example.trabajofinalpsp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.trabajofinalpsp.databinding.ActivityMainBinding
import com.example.trabajofinalpsp.objetos.Cliente
import com.example.trabajofinalpsp.objetos.User
import com.google.android.material.snackbar.Snackbar
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonActions()

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

            //  System.out.println(new String(resumen));
            //  System.out.println(hexadecimal(resumen))
            return hexadecimal(resumen)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }

    private fun buttonActions() {
        binding.botonAceptar.setOnClickListener{
            var hilo = Cliente()
            hilo.start()
            guardar(binding.root)
            loguin(binding.root)
        }
    }

    private fun loguin(view: View) {
        val login= Intent(this,LoginActivity::class.java)
        startActivity(login)
    }

    private fun guardar(view: View) {
        if (binding.editUsuario.text.isNotEmpty() && binding.editContrasenia.text.isNotEmpty()){
            var contrasenia = metodohash(binding.editContrasenia.text.toString());
            var nuevoUsuario:User = (User(binding.editUsuario.text.toString(),contrasenia.toString()))

            var pref=getSharedPreferences(nuevoUsuario.nombre, Context.MODE_PRIVATE) //text_nombre?.text.toString()
            var editor=pref.edit()

            // arrPersonaRec.add(persona)  Me faltaria meterle una persona completa
            editor.putString("nombre",nuevoUsuario.nombre)
            editor.putString("pass",nuevoUsuario.contrasenia)


            //mandamos los datos
            editor.commit()
            //aviso y luego borro el nombre y pass
            Snackbar.make(binding.root,"Registro completado", Snackbar.LENGTH_SHORT).show()
            binding.editUsuario.setText("")
            binding.editContrasenia.setText("")

        }else{
            Snackbar.make(binding.root,"Complete el registro", Snackbar.LENGTH_SHORT).show()
        }
    }


}