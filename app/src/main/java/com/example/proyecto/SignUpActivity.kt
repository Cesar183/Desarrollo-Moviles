package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

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
        val etConfirrContra = findViewById<EditText>(R.id.etConfirmPassword)
        val etTelefono = findViewById<EditText>(R.id.etPhone)
        val cbTerminos = findViewById<CheckBox>(R.id.cbTerms)
        if(etNombres.getText().toString().trim().equals(""))
        {
            etNombres.setError("This field can not be blank");
        }
        else if(etApellidos.getText().toString().trim().equals(""))
        {
            etApellidos.setError("This field can not be blank");
        }
        else if(etCorreo.getText().toString().trim().equals(""))
        {
            etCorreo.setError("This field can not be blank");
        }
        else if(etContraseña.getText().toString().trim().equals(""))
        {
            etContraseña.setError("This field can not be blank");
        }
        else if(etConfirrContra.getText().toString().trim().equals(""))
        {
            etConfirrContra.setError("This field can not be blank");
        }
        else if(etTelefono.getText().toString().trim().equals(""))
        {
            etTelefono.setError("This field can not be blank");
        }
        else if(!cbTerminos.isChecked())
        {
            Toast.makeText(this, "You must accept terms and conditions", Toast.LENGTH_LONG).show()
        }
        else
        {
            Toast.makeText(this, "Successfully registered user", Toast.LENGTH_LONG).show()
            etNombres.setText("")
            etApellidos.setText("")
            etCorreo.setText("")
            etContraseña.setText("")
            etConfirrContra.setText("")
            etTelefono.setText("")
            cbTerminos.isChecked = false
        }
    }

}