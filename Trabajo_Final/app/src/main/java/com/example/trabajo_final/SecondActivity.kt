package com.example.trabajo_final
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.trabajo_final.databinding.ActivitySecondBinding
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*


class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding;
    var tx_nombre_s: EditText?=null
    var tx_contr_s: EditText?=null
    private var arrPersonaRec=ArrayList<Usuario>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        asocio()
        acciones()


    }

    private fun acciones() {
        binding.bntAtras.setOnClickListener{
            atras()
        }
        binding.btnLog.setOnClickListener{

            login(binding.root)

        }
    }

    fun atras() {
        //me lleva al mainAct
        val mainActivity= Intent(this,MainActivity::class.java)
        startActivity(mainActivity)
        finish()
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

    fun login(view:View){
        var passConvertido = metodohash(tx_contr_s?.text.toString());
        var persona:Usuario = (Usuario(tx_nombre_s?.text.toString(),passConvertido.toString()))
        //aqui traemos los datos del usuario
        var pref=getSharedPreferences(persona.nombre,Context.MODE_PRIVATE)
        var pass=pref.getString("pass","")

        //si el pass coincide
        if ((pass==persona.contrasenia) ){//tx_contr_s?.text.toString()
            Toast.makeText(this,"Usuario logueado",Toast.LENGTH_SHORT).show()
            //TODO ME FALTA DE ACÁ DIRECCIONARLA A LA 3RA ACTIVITY Y ME FALTA METER EL HASH COMO CONTRASEÑA
            val login=Intent(this,Third_Activity::class.java)
            startActivity(login)

        }else{
            Toast.makeText(this,"Su usuario o contraseña no coincide",Toast.LENGTH_SHORT).show()
        }
        //en cualquiera de los dos casos
        tx_nombre_s?.setText("")
        tx_contr_s?.setText("")


    }



    private fun asocio() {
        tx_nombre_s=binding.txNombreS
        tx_contr_s=binding.txContrS
    }



}