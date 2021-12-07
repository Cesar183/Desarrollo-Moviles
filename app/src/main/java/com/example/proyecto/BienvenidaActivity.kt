package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.proyecto.databinding.ActivityBienvenidaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class BienvenidaActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityBienvenidaBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityBienvenidaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        //val btnCerrar = findViewById<Button>(R.id.btnClose)
        binding.btnClose.setOnClickListener {
            cerrarSesion()
        }
        binding.btnAdicionarPregunta.setOnClickListener{
            agregarPreguntas()
        }
    }
    private fun cerrarSesion()
    {
        auth.signOut()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun agregarPreguntas()
    {
        val intent = Intent(this, AdicionarPreguntas::class.java)
        startActivity(intent)
    }
}