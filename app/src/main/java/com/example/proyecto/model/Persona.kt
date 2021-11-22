package com.example.proyecto.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persona")
data class Persona(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var nombre : String,
    var apellidos : String,
    var correo : String,
    var contra : String,
    var telefono : String
)

