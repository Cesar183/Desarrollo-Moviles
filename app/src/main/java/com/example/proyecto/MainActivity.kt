package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.proyecto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener() {
            ValidarCredenciales()
        }
        binding.btnSignUp.setOnClickListener {
            NewUser()
        }
        binding.btnBack.setOnClickListener{
            Volver()
        }
    }

    private fun Volver()
    {
        val intentVolver = Intent(this, PrincipalActivity::class.java)
        startActivity(intentVolver)
    }
    private fun NewUser()
    {
        val intentSignUp = Intent(this, SignUpActivity::class.java)
        startActivity(intentSignUp)
    }

    //Usuario: correo@email.com
    //Contraseña: 1234
    private fun ValidarCredenciales()
    {
        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etContraseña = findViewById<EditText>(R.id.etContraseña)
        if(etUsuario.text.toString() == "correo@email.com")
        {
            if(etContraseña.text.toString().equals( "1234"))
            {
                val intentBienvenido = Intent(this, BienvenidaActivity::class.java)
                etUsuario.setText("")
                etContraseña.setText("")
                startActivity(intentBienvenido)
            }
            else
            {
                Toast.makeText(this, "password invalid", Toast.LENGTH_LONG).show()
            }
        }
        else
        {
            Toast.makeText(this, "user invalid", Toast.LENGTH_LONG).show()
        }
    }
}
