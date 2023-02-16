package com.example.trabajopsp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.trabajopsp.databinding.ActivityMainBinding
import com.example.trabajopsp.model.Usuario
import java.io.*
import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var clave: SecretKey
    private lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //acciones()
        guardarClave()
    }

    fun acciones() {
        binding.botonAceptar.setOnClickListener {
            if (binding.editUsuario.text.isNotEmpty()) {
                var intent = Intent(applicationContext, SecondActivity::class.java);
                var bundle = Bundle();
                bundle.putString("nombre", binding.editUsuario.text.toString())
                intent.putExtras(bundle);
                startActivity(intent)
                binding.editUsuario.setText("")
            }
        }
    }

    fun guardarClave() {
        try {
            val kg = KeyGenerator.getInstance("AES")
            kg.init(128)
            clave = kg.generateKey()
            usuario = Usuario("Alex", clave)
            val c = Cipher.getInstance("AES/ECB/PKCS5Padding")
            c.init(Cipher.ENCRYPT_MODE, usuario.clave)
            //cifro la clave
            val textoPlano = "adios".toByteArray()
            val textoCifrado = c.doFinal(textoPlano)
            println("Encriptado: " + String(textoCifrado))
            //guardo la clave en un fichero
            var out = ObjectOutputStream(FileOutputStream("Clave.secreta"));
            out.writeObject(usuario.nombre);
            out.writeObject(usuario.clave);
            out.close();

            //forma de descifrar el texto
            /*c.init(Cipher.DECRYPT_MODE, clave)
            val desencriptado = c.doFinal(textoCifrado)
            println("Desencriptado: " + String(desencriptado))*/
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    //Esto es para LEER lo que hay dentro del fichero    - NO LO PONGO PORQUE NO SE ESCRIBIRLO
    //la carpeta y el directorio lo creamos a mano
    // la carpeta raw no se puede cambiar porque es el requisito para el openRawResources
    /*val text: String;
    val filename = "ficheros"
    val inputStream = resources.openRawResource( resources.getIdentifier(filename,"raw", packageName))
    var inputStreamReader: InputStreamReader = InputStreamReader(inputStream)
    var bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
    text =  bufferedReader.readLine().toString()
    println(text)*/
}

