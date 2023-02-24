package com.example.trabajofinalpsp.objetos

import java.io.PrintWriter
import java.net.Socket

class Cliente() : Thread() {

    private lateinit var client: Socket
    private lateinit var out: PrintWriter

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

}