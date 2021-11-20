package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val btnSignUp = findViewById<Button>(R.id.btnSignUpRegister)
        val btnRegresarLog = findViewById<Button>(R.id.btnBackLogin)
        btnSignUp.setOnClickListener {
            ValidarInformacion()
        }
        btnRegresarLog.setOnClickListener {
            Regresar()
        }
    }
    private fun Regresar()
    {
        val intentLog = Intent(this, MainActivity::class.java)
        startActivity(intentLog)
    }
    private fun ValidarInformacion()
    {
        val etNombres = findViewById<EditText>(R.id.etName)
        val etApellidos = findViewById<EditText>(R.id.etLastName)
        val etCorreo = findViewById<EditText>(R.id.etEmail)
        val etContraseña = findViewById<EditText>(R.id.etPassword)
        val etTelefono = findViewById<EditText>(R.id.etPhone)
        val cbTerminos = findViewById<CheckBox>(R.id.cbTerms)
        //Linea para creacion del error por campo vacio
        val error: String by lazy { resources.getString(R.string.txtField) }
        val terminos: String by lazy { resources.getString(R.string.txtFieldTerms) }
        val registrado: String by lazy { resources.getString(R.string.registered) }
        if(etNombres.getText().toString().trim().equals(""))
        {
            etNombres.setError(error, null)
        }
        else if(etApellidos.getText().toString().trim().equals(""))
        {
            etApellidos.setError(error, null)
        }
        else if(etCorreo.getText().toString().trim().equals(""))
        {
            etCorreo.setError(error, null)
        }
        else if(etContraseña.getText().toString().trim().equals(""))
        {
            etContraseña.setError(error, null)
        }

        else if(etTelefono.getText().toString().trim().equals(""))
        {
            etTelefono.setError(error, null)
        }
        else if(!cbTerminos.isChecked())
        {
            Toast.makeText(this,terminos,  Toast.LENGTH_LONG).show()
        }
        else
        {
            Toast.makeText(this,registrado,  Toast.LENGTH_LONG).show()
            etNombres.setText("")
            etApellidos.setText("")
            etCorreo.setText("")
            etContraseña.setText("")
            etTelefono.setText("")
            cbTerminos.isChecked = false
        }
    }
    private fun validarContrasena ( pass: String): Boolean {
        var pattern: Pattern
        var matcher: Matcher

        val PATRON_CONTRASENA = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@!%*?&])(?=\\S+$).{8,15}$"

        pattern = Pattern.compile( PATRON_CONTRASENA )
        matcher = pattern.matcher( pass )

        return matcher.matches()
    }
}