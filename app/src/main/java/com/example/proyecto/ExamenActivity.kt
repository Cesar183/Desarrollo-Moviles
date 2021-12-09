package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.proyecto.databinding.ActivityExamenBinding
import com.example.proyecto.model.Pregunta
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class ExamenActivity : AppCompatActivity()
{
    lateinit var binding: ActivityExamenBinding
    private lateinit var listaPreguntas: ArrayList<Pregunta>
    private lateinit var preguntaAdapter: ArrayAdapter<Pregunta>

    //conexion con la base de datos de Firebase
    val database = Firebase.database
    var dbReferenciaPregunta = database.getReference("preguntas")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityExamenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Conexion a la base de datos de Firebas
        Firebase.initialize(this)
        listaPreguntas = ArrayList<Pregunta>()

        binding.btnBack.setOnClickListener{
            salirExamen()
        }
        verListadoPreguntas()
    }

    private fun verListadoPreguntas()
    {
        val preguntaItemListener = object: ValueEventListener
        {
            override fun onDataChange(dataSnapshot: DataSnapshot)
            {
                for(pre in dataSnapshot.children)
                {
                    var pregunta = Pregunta("","","","","")
                    //Objecto MAP
                    val mapPregunta: Map<String,Any> = pre.value as HashMap<String, Any>

                    pregunta.id = mapPregunta.get("id").toString()
                    pregunta.Pregunta = mapPregunta.get("pregunta").toString()
                    pregunta.Respuesta1 = mapPregunta.get("respuesta1").toString()
                    pregunta.Respuesta2 = mapPregunta.get("respuesta2").toString()
                    pregunta.Respuesta3 = mapPregunta.get("respuesta3").toString()

                    listaPreguntas.add(pregunta)
                    preguntaAdapter = PreguntasAdapter(this@ExamenActivity, listaPreguntas)
                    binding.lvTest.adapter = preguntaAdapter
                }
            }

            override fun onCancelled(error: DatabaseError)
            {
                TODO("Not yet implemented")
            }

        }
        dbReferenciaPregunta.addValueEventListener(preguntaItemListener)
    }

    private fun salirExamen()
    {
        intent = Intent(this, BienvenidaActivity::class.java)
        startActivity(intent)
    }
}