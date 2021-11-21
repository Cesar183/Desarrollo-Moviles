package com.example.proyecto.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyecto.dao.MateriaDAO
import com.example.proyecto.model.Materia

@Database(entities = [Materia::class], version = 1)
abstract class MateriaDB : RoomDatabase()
{
    //Permite realizar las operaciones basicas de una DB
    abstract fun materiaDao(): MateriaDAO

    //Generar la instancia de la BD
    companion object
    {
        @Volatile
        private var INSTANCE : MateriaDB? = null

        fun getDatabase(context: Context) : MateriaDB
        {
            //Si la DB ya existe
            val tempInstancia = INSTANCE
            if (tempInstancia != null)
            {
                return tempInstancia
            }
            else // Si la DB no existe, crea la DB
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MateriaDB::class.java,
                    "app_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}