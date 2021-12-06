package com.example.proyecto

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.proyecto.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity()
{
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Inicializar variable Firebase
        auth = Firebase.auth

        binding.btnLogin.setOnClickListener() {
            //ValidarCredenciales()
            Login(binding.etUsuario.text.toString(), binding.etPass.text.toString())
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
        val etContraseña = findViewById<EditText>(R.id.etPass)
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
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            verActividadBienvenida()
        }
    }
    private fun Login(email: String, pass: String)
    {
        val autenticado: String by lazy { resources.getString(R.string.AuthenticationSuccesfull)}
        val autenticacionFallida: String by lazy { resources.getString(R.string.AuthenticationFailed)}
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, autenticado,
                        Toast.LENGTH_SHORT).show()
                    verActividadBienvenida()
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, autenticacionFallida,
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }
    private fun verActividadBienvenida()
    {
        val intentBienvenido = Intent(this, BienvenidaActivity::class.java)
        binding.etUsuario.setText("")
        binding.etPass.setText("")
        startActivity(intentBienvenido)
    }
}
