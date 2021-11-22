package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.proyecto.database.PersonaDB
import com.example.proyecto.model.Persona
import kotlinx.android.synthetic.main.activity_lista_personas.*


class ListaPersonasActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_personas)

        var listaPersonas = emptyList<Persona>()

        //Conexion a la DB
        val database = PersonaDB.getDatabase(this)

        //consultar las personas
        database.personaDAO().getAll().observe(this, Observer {
            listaPersonas = it
            val adapter = PersonaAdapter(this, listaPersonas)
            // Mostrar en el listView de personas
            lvPersona.adapter = adapter
        })
    }
}