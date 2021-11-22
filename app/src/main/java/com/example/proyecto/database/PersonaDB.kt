package com.example.proyecto.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyecto.dao.PersonaDAO
import com.example.proyecto.model.Persona

@Database(entities = [Persona::class], version = 1)
abstract class PersonaDB : RoomDatabase()
{
    //Permite realizar las operaciones basicas de una DB
    abstract fun personaDAO(): PersonaDAO

    //Generar la instancia de la BD
    companion object
    {
        @Volatile
        private var INSTANCE : PersonaDB? = null

        fun getDatabase(context: Context) : PersonaDB
        {
            //Si la DB ya existe
            val tempInstancia = INSTANCE
            if (tempInstancia != null)
            {
                return tempInstancia
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonaDB::class.java,
                    "app_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}