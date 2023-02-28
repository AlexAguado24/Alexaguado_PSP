package com.example.trabajo_final
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.trabajo_final.databinding.ActivityMainBinding
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.PrintWriter
import java.net.Socket
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    lateinit var persona :Usuario
    lateinit var passConvertido:Any;
    var text_nombre:EditText?=null
    var text_contr:EditText?=null
    private var arrPersonaRec=ArrayList<Usuario>();
//##########Conect

lateinit var bf:BufferedReader;

    //###########Fin conect
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        asocio()

        acciones()

    }

    private fun acciones() {

        binding.btnRegistrar.setOnClickListener{
//############################         ENVÍO LOS DATOS A JAVA      #################################
            class Enviar: Thread() {
                private lateinit var client: Socket
                private lateinit var out: PrintWriter
                private lateinit var br: BufferedOutputStream

                override fun run() {
                    super.run()


                    //println("serch")
                    client = Socket("192.168.68.107", 1234)
                    out = PrintWriter(client.getOutputStream(), true)
                    br = BufferedOutputStream(client.getOutputStream())
                    Log.v("manda","${persona.nombre}")
                     out.write(persona.nombre +" ")
                     out.write(passConvertido.toString())
                    out.close()
                    client.close()
                    /*Pasar Strings y crear el objeto desde el servidor, cifrando la contraseña*/

                    if (binding.btnRegistrar.text=="Registrar") {
                        arrPersonaRec.add(persona)
                    };

                }
               }
            //Envio el hilo
            var usuarioEn:Enviar=Enviar();
            usuarioEn.start()
//############################         ENVÍO LOS DATOS A JAVA  FIN      #################################


            guardar(binding.root)

        }


        binding.btnSalir.setOnClickListener {

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

            return hexadecimal(resumen)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }


    fun guardar(view:View) {
       //creamos el edit se almacena dentro del dispositivo en data, data, com.example.trabajo_final, shared_prefs

        fun regUsu(){
            passConvertido = metodohash(text_contr?.text.toString())!!;
            persona = (Usuario(text_nombre?.text.toString(),passConvertido.toString()))

            //     Toast.makeText(this,"Iniciando",Toast.LENGTH_SHORT).show()
            Log.v("ContraEnviada",persona.contrasenia)
            text_nombre?.setText("")
            text_contr?.setText("")
        }





        if (text_nombre?.text.toString()=="0" && text_contr?.text?.toString()=="0"){
            regUsu()
            //CIERRA LA CARGA DE USUARIO Y COMPARA
            binding.regUsu.setText("INICIANDO SECION")
            binding.btnRegistrar.setText("Iniciar")



        }
        else if (text_nombre?.text.toString()=="1" && text_contr?.text?.toString()=="0"){

            //CIERRA LA COMPARACIÓN
            binding.regUsu.setText("USUARIO LOGUEADO")
            binding.btnRegistrar.setText("Log")
            regUsu()


        }else if (text_nombre?.text.toString()=="" && text_contr?.text.toString()==""){
            Toast.makeText(this, "Complete los campos para su registro", Toast.LENGTH_SHORT).show()
        }  else{

            arrPersonaRec.forEach {
                println("alex te vas a olvidar")
                if (it.nombre==text_nombre?.text.toString()){
                    println("son iguales")
                }
            }
            regUsu()
        }


    }


    private fun asocio() {
        text_nombre=binding.textNombre
        text_contr=binding.textContr
    }

}


