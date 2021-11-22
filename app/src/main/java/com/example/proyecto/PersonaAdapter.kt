package com.example.proyecto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.proyecto.model.Persona
import kotlinx.android.synthetic.main.persona_list_item.view.*

class PersonaAdapter(private val mContext: Context, val listaPersonas: List<Persona>)
    :ArrayAdapter<Persona>(mContext, 0, listaPersonas)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.persona_list_item, parent, false)
        val persona = listaPersonas[position]


        layout.tvNombre.text = persona.nombre
        layout.tvApellidos.text = persona.apellidos
        layout.tvCorreo.text = persona.correo
        layout.tvContra.text = persona.contra
        layout.tvTelefono.text = persona.telefono

        return layout
    }
}