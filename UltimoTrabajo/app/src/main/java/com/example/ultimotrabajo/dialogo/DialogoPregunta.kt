package com.example.ultimotrabajo.dialogo

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.ultimotrabajo.R

class DialogoPregunta: DialogFragment() {

    private lateinit var listener: OnMarcaListener
    private lateinit var vista : View;
    private lateinit var editMarca : EditText;
    private lateinit var botonAceptar : Button;


    override fun onAttach(context: Context) {
        super.onAttach(context)
        vista = LayoutInflater.from(context).inflate(R.layout.dialogo_item,null)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(requireContext())
        builder.setView(vista)
        return builder.create()
    }

    override fun onStart() {
        super.onStart()
        editMarca = vista.findViewById(R.id.edit_dialogo_zapas)
        botonAceptar = vista.findViewById(R.id.boton_dialogo_aceptar)
    }

    override fun onResume() {
        super.onResume()
        botonAceptar.setOnClickListener {
            listener.onMarcaSeleccionada(editMarca.toString())
            dismiss()
        }
    }

    interface OnMarcaListener{
        fun onMarcaSeleccionada(marca:String)
    }


}