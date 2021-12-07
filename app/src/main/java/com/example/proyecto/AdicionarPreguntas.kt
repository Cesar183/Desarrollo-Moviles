package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyecto.databinding.ActivityAdicionarPreguntasBinding
import com.example.proyecto.databinding.ActivitySignUpBinding
import com.example.proyecto.model.Pregunta
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import kotlinx.android.synthetic.main.activity_adicionar_preguntas.*
import java.util.*

class AdicionarPreguntas : AppCompatActivity()
{
    private lateinit var binding: ActivityAdicionarPreguntasBinding
    val database = Firebase.database
    val dbReferencePreguntas = database.getReference("preguntas")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityAdicionarPreguntasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Firebase.initialize(this)

        binding.btnGuardar.setOnClickListener{
            val registrada: String by lazy { resources.getString(R.string.questionRegistered) }
            guardarPregunta()

            Toast.makeText(this,registrada,  Toast.LENGTH_LONG).show()
            etPregunta.setText("")
            etRespuestaUno.setText("")
            etRespuestaDos.setText("")
            etRespuestaTres.setText("")
        }

        binding.btnVolver.setOnClickListener {
            regresar()
        }
    }
    private fun guardarPregunta()
    {
        val pregunta = Pregunta(
            UUID.randomUUID().toString(), // Id para guardar la pelicula
            binding.etPregunta.text.toString(),
            binding.etRespuestaUno.text.toString(),
            binding.etRespuestaDos.text.toString(),
            binding.etRespuestaTres.text.toString()
        )
        dbReferencePreguntas.child(pregunta.id).setValue(pregunta)
    }
    private fun regresar()
    {
        val intent = Intent(this, BienvenidaActivity::class.java)
        startActivity(intent)
    }
}