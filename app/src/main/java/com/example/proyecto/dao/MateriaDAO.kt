package com.example.proyecto.dao

import androidx.room.*
import com.example.proyecto.model.Materia

@Dao
interface MateriaDAO
{
    @Query("SELECT * FROM materia")
    fun getAll() : List<Materia>

    @Query("SELECT * FROM materia WHERE id = :id")
    fun getMateriaPorId(id : Int): Materia

    @Insert()
    fun insertar(materia: Materia)

    @Update
    fun actualizar(materia: Materia)

    @Delete
    fun eliminar(materia: Materia)
}