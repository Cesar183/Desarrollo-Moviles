package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.proyecto.databinding.ActivityBienvenidaBinding

class BienvenidaActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityBienvenidaBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityBienvenidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val btnCerrar = findViewById<Button>(R.id.btnClose)
        binding.btnClose.setOnClickListener {
            finish()
        }
    }
}