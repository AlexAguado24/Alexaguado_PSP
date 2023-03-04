package com.example.ultimotrabajo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ultimotrabajo.objetos.Zapatillas

class ZapatillasAdapter(var contexto: Context, var listaZapatillas: ArrayList<Zapatillas>) :
    RecyclerView.Adapter<ZapatillasAdapter.MyHolder>() {


    inner class MyHolder(vista: View) : RecyclerView.ViewHolder(vista) {
        lateinit var imagen : ImageView
        lateinit var marca : TextView
        lateinit var tipo : TextView
        init {
            imagen = vista.findViewById(R.id.imagen_zapa)
            marca = vista.findViewById(R.id.marca_zapas)
            tipo = vista.findViewById(R.id.tipo_zapas)
        }
    }

    fun cambiarLista(listaNueva:ArrayList<Zapatillas>){
        this.listaZapatillas = listaNueva;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var vista = LayoutInflater.from(contexto).inflate(R.layout.recycler_item_zapas,parent,false);
        return MyHolder(vista)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var zapatillas = listaZapatillas[position];
        holder.imagen.setImageResource(zapatillas.imagen)
        holder.marca.setText(zapatillas.tipo)
        holder.tipo.setText(zapatillas.nombre)
    }

    override fun getItemCount(): Int {
        return listaZapatillas.size
    }

}