package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BienvenidaActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida)

        val btnCerrar = findViewById<Button>(R.id.btnClose)
        btnCerrar.setOnClickListener {
            finish()
        }
    }
}