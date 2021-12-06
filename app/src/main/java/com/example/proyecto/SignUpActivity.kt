package com.example.proyecto

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.proyecto.database.PersonaDB
import com.example.proyecto.databinding.ActivitySignUpBinding
import com.example.proyecto.model.Persona
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.persona_list_item.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity()
{
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Inicializar servicio de Firebase
        auth = Firebase.auth

        binding.btnSignUpRegister.setOnClickListener{
            //ValidarInformacion()
            RegistrarUsuario()
        }
        binding.btnBackLogin.setOnClickListener {
            Regresar()
        }
    }

    private fun Regresar()
    {
        val intentLog = Intent(this, MainActivity::class.java)
        startActivity(intentLog)
    }
    private fun RegistrarUsuario()
    {
        val nombre = binding.etName.text.toString()
        val apellidos = binding.etLastName.text.toString()
        val email = binding.etEmail.text.toString()
        val pass = binding.etPassword.text.toString()
        val telefono = binding.etPhone.text.toString()

        val autenticado: String by lazy { resources.getString(R.string.AuthenticationSuccesfull)}
        val autenticacionFallida: String by lazy { resources.getString(R.string.AuthenticationFailed)}

        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, autenticado,
                        Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    verActivityMain()
                } else {
                    Toast.makeText(baseContext, autenticacionFallida,
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }
    private fun verActivityMain()
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
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

            //RegistrarEnDB()
        }
    }
    private fun RegistrarEnDB()
    {
        lateinit var context: Context
        //val context = activity?.applicationContext
        //Intanciar objeto persona para guardar en la DB
        val persona = Persona(0, "${etName.text}","${etLastName.text}","${etEmail.text}","${etPassword.text}","${etPhone.text}")
        //Inserta en la DB usando Coroutine
        CoroutineScope(Dispatchers.IO).launch {
            val database = context?.let{PersonaDB.getDatabase(it)}
            if(database != null)
            {
                database.personaDAO().insertar(persona)
            }
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