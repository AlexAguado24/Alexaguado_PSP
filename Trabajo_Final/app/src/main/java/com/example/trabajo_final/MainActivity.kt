package com.example.trabajo_final

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.trabajo_final.databinding.ActivityMainBinding
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    var text_nombre:EditText?=null
    var text_contr:EditText?=null
    private var arrPersonaRec=ArrayList<Usuario>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        asocio()

        acciones()



    }






    private fun acciones() {

        binding.btnIniciar.setOnClickListener{
            var hilo = Cliente2()
            hilo.start()
            guardar(binding.root)
            irLoguin(binding.root)

        }
        binding.btnSalir.setOnClickListener {

            Toast.makeText(this,"Hasta luego",Toast.LENGTH_SHORT).show()
            finish()
        }
    }


    // HASH Y CIFRADO

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



    fun guardar(view:View) {
       //creamos el edit se almacena dentro del dispositivo en data, data, com.example.trabajo_final, shared_prefs



        if (text_nombre?.text.toString()!="" && text_contr?.text.toString()!=""){
            var passConvertido = metodohash(text_contr?.text.toString());
            var persona:Usuario = (Usuario(text_nombre?.text.toString(),passConvertido.toString()))

            var pref=getSharedPreferences(persona.nombre,Context.MODE_PRIVATE) //text_nombre?.text.toString()
            var editor=pref.edit()

            // arrPersonaRec.add(persona)  Me faltaria meterle una persona completa
            editor.putString("nombre",persona.nombre)
            editor.putString("pass",persona.contrasenia)


            //mandamos los datos
            editor.commit()
            //aviso y luego borro el nombre y pass
            Toast.makeText(this,"Cuenta creada",Toast.LENGTH_SHORT).show()
            Log.v("ContraEnviada",persona.contrasenia)
            text_nombre?.setText("")
            text_contr?.setText("")
        }else{
            Toast.makeText(this,"Complete los campos para su registro",Toast.LENGTH_SHORT).show()
        }


/*         Treaté de meterlo en un fichero interno pero no lo podia escribir,esto lo que hace es ponerlo en un fichero interno, el editor
        var text="HOLA"
        val filename = "informacion"
        val inputStream=resources.openRawResource(resources.getIdentifier(filename,"raw",packageName))
        var inputStreamReader: InputStreamReader = InputStreamReader(inputStream)
        var bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
        text=bufferedReader.readLine().toString()*/

    }
    fun irLoguin(view: View){
        val login=Intent(this,SecondActivity::class.java)
        startActivity(login)
    }


    private fun asocio() {
        text_nombre=binding.textNombre
        text_contr=binding.textContr
    }





}