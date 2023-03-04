package com.example.ultimotrabajo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ultimotrabajo.databinding.ActivityPrincipalBinding
import com.example.ultimotrabajo.dialogo.DialogoPregunta
import com.example.ultimotrabajo.objetos.Zapatillas


class PrincipalActivity : AppCompatActivity(), DialogoPregunta.OnMarcaListener {

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
        listaZapas.add(Zapatillas("Forum", "adidas", R.drawable.adidas_forum))
        listaZapas.add(Zapatillas("Samba", "adidas", R.drawable.samba))
        listaZapas.add(Zapatillas("Green", "adidas", R.drawable.adiddas_verde))
        listaZapas.add(Zapatillas("Air Force One", "nike", R.drawable.air_force))
        listaZapas.add(Zapatillas("Air Jordan", "nike", R.drawable.air_jordan))
        listaZapas.add(Zapatillas("Air Max", "nike", R.drawable.air_max))
        listaZapas.add(Zapatillas("Smash 2", "puma", R.drawable.puma_smash2))
        listaZapas.add(Zapatillas("Ferrari", "puma", R.drawable.puma_ferrari))
        listaZapas.add(Zapatillas("California", "puma", R.drawable.puma_cali))
        listaZapas.add(Zapatillas("Manteca", "dc", R.drawable.dc_manteca))
        listaZapas.add(Zapatillas("Red DC", "dc", R.drawable.dc_rojas))
        listaZapas.add(Zapatillas("Vandium", "dc", R.drawable.dc_vandium))
        adapterZapas = ZapatillasAdapter(this, listaZapas);
        binding.recyclerZapas.adapter = adapterZapas
        binding.recyclerZapas.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }

    private fun butonActions() {
        binding.botonBuscar.setOnClickListener {
            DialogoPregunta().show(supportFragmentManager, "")
        }
    }

    override fun onMarcaSeleccionada(marca: String) {
        adapterZapas.cambiarLista(adapterZapas.listaZapatillas.filter {
            it.tipo == marca
        } as ArrayList<Zapatillas>)
        binding.textConteo.setText(listaZapas.count())
    }
}