package com.example.trabajopsp.model

import android.widget.Toast
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.net.ServerSocket
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class GenerarContrasenia {

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
            System.out.println(hexadecimal(resumen))
            return hexadecimal(resumen)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }

    fun cargar(): Usuario? {
        //var text=""    var persona:Usuario?=null
        try {
            //ruta en el movil
            val ruta = baseContext.getExternalFilesDir(null)?.absolutePath
            val miCarpeta = File(ruta, "datos")
            if (!miCarpeta.exists()) {
                miCarpeta.mkdir()
            }
            val ficheroF = File(miCarpeta, "datos.txt")
            val fichero = BufferedReader(InputStreamReader(FileInputStream(ficheroF)))
            persona = fichero.use(BufferedReader::readText) as Usuario

        }catch (e:java.lang.Exception){

        }
        return persona!!
    }

    fun guardar(persona: Usuario){
        try {
            //ruta en el movil
            val ruta = baseContext.getExternalFilesDir(null)?.absolutePath
            val miCarpeta=File(ruta,"datos")
            if (!miCarpeta.exists()) {
                miCarpeta.mkdir()}
            val ficheroF = File(miCarpeta,"datos.txt")
            ficheroF.appendText("${persona}\n")
        }catch (e:Exception){
            Toast.makeText(this,"No se pudo guardar", Toast.LENGTH_LONG).show()
        }
    }

    private fun acciones() {
        binding.botonAgregar.setOnClickListener{
            guardar(personaRec)
            binding.tvConte.text= cargar().toString();
            Log.v("datos_pasados", "RECIVE ${personaRec}")
        }
        binding.botonBor.setOnClickListener{
            borrar()
            binding.tvConte.text= cargar().toString();
        }
    }

    fun borrar(){
        val ruta = baseContext.getExternalFilesDir(null)?.absolutePath
        val miCarpeta = File(ruta,"datos")
        val ficheroF = File(miCarpeta,"datos.txt")
        val result=ficheroF.delete()
        if (result) {
            Toast.makeText(this,"Usuarios BORRADOS", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this,"Usuarios NO BORRADOS", Toast.LENGTH_SHORT).show()
        }
    }
}