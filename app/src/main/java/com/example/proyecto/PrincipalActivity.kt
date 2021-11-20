package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class PrincipalActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        setSupportActionBar(findViewById(R.id.myToolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.menu_principal_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId)
    {
        R.id.action_settings ->
        {
            Toast.makeText(this, "Configuraciones", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.action_log ->
        {
            //Toast.makeText(this, "Ingresar", Toast.LENGTH_SHORT).show()
            val intentLog = Intent(this, MainActivity::class.java)
            startActivity(intentLog)
            true
        }
        R.id.action_logout ->
        {
            Toast.makeText(this, "Salir", Toast.LENGTH_SHORT).show()
            true
        }
        else ->
        {
            super.onOptionsItemSelected(item)
        }
    }
}