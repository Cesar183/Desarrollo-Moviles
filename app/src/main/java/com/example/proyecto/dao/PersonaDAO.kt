package com.example.proyecto.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.proyecto.model.Persona

@Dao
interface PersonaDAO
{
    @Query("SELECT * FROM persona")
    fun getAll() : LiveData<List<Persona>>

    @Query("SELECT * FROM persona WHERE id = :id")
    fun getMateriaPorId(id : Int): Persona

    @Insert()
    fun insertar(persona: Persona)

    @Update
    fun actualizar(persona: Persona)

    @Delete
    fun eliminar(persona: Persona)
}