package com.example.ultimotrabajo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.ultimotrabajo.databinding.ActivityMainBinding
import com.example.ultimotrabajo.objetos.Usuario
import java.io.BufferedOutputStream
import java.io.PrintWriter
import java.net.Socket
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var usuario: Usuario
    lateinit var passConvertido: Any;
    private var arrPersonaRec = ArrayList<Usuario>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonActions()

    }



    private fun buttonActions() {
        binding.botonAceptar.setOnClickListener {
            class Enviar : Thread() {
                private lateinit var client: Socket
                private lateinit var out: PrintWriter
                private lateinit var br: BufferedOutputStream

                override fun run() {
                    super.run()
                    client = Socket("192.168.2.13", 1234)
                    out = PrintWriter(client.getOutputStream(), true)
                    br = BufferedOutputStream(client.getOutputStream())
                    out.write(usuario.nombre + " ")
                    out.write(passConvertido.toString())
                    out.close()
                    client.close()
                    //cifra la contraseñ y lo pasa a java
                    if (binding.botonAceptar.text == "Registrar") {
                        //añade usuarios a un arraylist
                        arrPersonaRec.add(usuario)
                    };
                }
            }

            var usuario = Enviar();
            usuario.start()
            guardar(binding.root)
        }
    }

    fun guardar(view: View) {
        fun regUsu() {
            passConvertido = metodohash(binding.editContrasenia.text.toString())!!;
            usuario = (Usuario(binding.editUsuario.text.toString(), passConvertido.toString()))
            binding.editUsuario.setText("")
            binding.editContrasenia.setText("")
        }
        if (binding.editUsuario.text.toString() == "0" && binding.editContrasenia.text?.toString() == "0") {
            regUsu()
            //deja de registrar y empieza a comparar
            binding.textoRegistro.setText("INICIANDO SECION")
            binding.botonAceptar.setText("Iniciar")
        } else if (binding.editUsuario.text.toString() == "1" && binding.editContrasenia.text?.toString() == "0") {
            //Termina de comparar
            binding.textoRegistro.setText("USUARIO LOGUEADO")
            binding.botonAceptar.setText("Loguear")
            regUsu()
        } else if (binding.editUsuario.text.toString() == "" && binding.editContrasenia.text.toString() == "") {
            Toast.makeText(this, "Complete los campos para su registro", Toast.LENGTH_SHORT).show()
        } else {
            arrPersonaRec.forEach {
                if (it.nombre == binding.editUsuario.text.toString()){
                    //cuando encuentra el usuario en la base de datos arranca la nueva pantalla
                    val intent= Intent(this, PrincipalActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            regUsu()
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