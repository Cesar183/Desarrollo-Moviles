package com.example.proyecto.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materia")
data class Materia(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var nombre : String,
    var duracion : String,
    var detalles : String
)

