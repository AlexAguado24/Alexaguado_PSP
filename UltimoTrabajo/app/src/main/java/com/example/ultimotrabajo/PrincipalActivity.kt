package com.example.ultimotrabajo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ultimotrabajo.databinding.ActivityPrincipalBinding
import com.example.ultimotrabajo.dialogo.DialogoPregunta
import com.example.ultimotrabajo.objetos.Zapatillas


class PrincipalActivity : AppCompatActivity(),DialogoPregunta.OnMarcaListener {

    private lateinit var binding: ActivityPrincipalBinding
    private lateinit var listaZapas: ArrayList<Zapatillas>
    private lateinit var marcaZapas: String
    private lateinit var adapterZapas: ZapatillasAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        butonActions()
        instancias();
    }

    private fun instancias() {
        listaZapas = ArrayList();
        listaZapas.add(Zapatillas("Forum","Adidas",R.drawable.adidas_forum))
        listaZapas.add(Zapatillas("Samba","Adidas",R.drawable.samba))
        listaZapas.add(Zapatillas("Green","Adidas",R.drawable.adiddas_verde))
        listaZapas.add(Zapatillas("Air Force One","Nike",R.drawable.air_force))
        listaZapas.add(Zapatillas("Air Jordan","Nike",R.drawable.air_jordan))
        listaZapas.add(Zapatillas("Air Max","Nike",R.drawable.air_max))
        listaZapas.add(Zapatillas("Smash 2","Puma",R.drawable.puma_smash2))
        listaZapas.add(Zapatillas("Ferrari","Puma",R.drawable.puma_ferrari))
        listaZapas.add(Zapatillas("California","Puma",R.drawable.puma_cali))
        listaZapas.add(Zapatillas("Manteca","DC",R.drawable.dc_manteca))
        listaZapas.add(Zapatillas("Red DC","DC",R.drawable.dc_rojas))
        listaZapas.add(Zapatillas("Vandium","DC",R.drawable.dc_vandium))
        adapterZapas.cambiarLista(adapterZapas.listaZapatillas.filter {
            it.tipo == marcaZapas
        } as ArrayList<Zapatillas>)
    }

    private fun butonActions() {
        binding.botonBuscar.setOnClickListener{
            DialogoPregunta().show(supportFragmentManager,"")
        }
    }

    override fun onMarcaSeleccionada(marca: String) {
        marcaZapas = marca

    }
}