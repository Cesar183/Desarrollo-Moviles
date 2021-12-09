package com.example.proyecto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.proyecto.model.Pregunta


class PreguntasAdapter (private val mContext: Context, val listaPeliculas: List<Pregunta>)
    : ArrayAdapter<Pregunta>( mContext,0, listaPeliculas)
{
    override fun getView(posicion: Int, view: View?, viewGroup: ViewGroup): View{

        val layout = LayoutInflater.from(mContext).inflate(R.layout.preguntas_list_item, viewGroup, false)
        val pregunta = listaPeliculas[posicion]

        layout.findViewById<TextView>(R.id.tvId).text = pregunta.id
        layout.findViewById<TextView>(R.id.tvPregunta).text = pregunta.Pregunta
        layout.findViewById<TextView>(R.id.tvRespuesta1).text = pregunta.Respuesta1
        layout.findViewById<TextView>(R.id.tvRespuesta2).text = pregunta.Respuesta2
        layout.findViewById<TextView>(R.id.tvRespuesta3).text = pregunta.Respuesta3

        return layout
    }
}