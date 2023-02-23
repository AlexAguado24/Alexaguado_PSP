package com.example.trabajo_final


import android.content.Context
import java.io.PrintWriter
import java.net.Socket


class Cliente2() : Thread() {

    private lateinit var client: Socket
    private lateinit var out: PrintWriter

    //   var pref=getSharedPreferences(persona.nombre, Context.MODE_PRIVATE)
    //   var pass=pref.getString("pass","")

    override fun run() {
        super.run()
        println("cualquier")
        //Hago conexion con servidor y envio las cordenadas/pulsacion del boton
        //Servidor y puerto los cojo de los Textview del activity_main2.xml
        client = Socket("192.168.56.1", 1234)
        out = PrintWriter(client.getOutputStream(), true)
        //Envio coordenadas/pulsacion boton derecho o izquierdo --> Depende de que se pulse
        out.println("cord")
        out.close()
        client.close()
    }
/*
        var Host = "192.168.2.220"
        var puerto = 5556 //puerto remoto


        // Propiedades JSSE)

        // Propiedades JSSE)

        System.setProperty("javax.net.ssl.trustStore", "java/com/example/trabajo_final/AlmacenSrv2")
        System.setProperty("javax.net.ssl.trustStorePassword", "1234567")

        println("PROGRAMA CLIENTE INICIADO....")

        var sfact = SSLSocketFactory.getDefault() as SSLSocketFactory
        var Cliente = sfact.createSocket(Host, puerto) as SSLSocket

        // CREO FLUJO DE SALIDA AL SERVIDOR

        // CREO FLUJO DE SALIDA AL SERVIDOR
        var flujoSalida = DataOutputStream(Cliente.outputStream)

        // ENVIO UN SALUDO AL SERVIDOR

        // ENVIO UN SALUDO AL SERVIDOR
        flujoSalida.writeUTF("Saludos al SERVIDOR DESDE EL CLIENTE")

        // CREO FLUJO DE ENTRADA AL SERVIDOR

        // CREO FLUJO DE ENTRADA AL SERVIDOR
        var flujoEntrada = DataInputStream(Cliente.inputStream)

        // EL servidor ME ENVIA UN MENSAJE

        // EL servidor ME ENVIA UN MENSAJE
        println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF())

        // CERRAR STREAMS Y SOCKETS

        // CERRAR STREAMS Y SOCKETS
        flujoEntrada.close()
        flujoSalida.close()
        Cliente.close()

    }// main*/
}


