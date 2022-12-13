package com.example.registroalumnos.BBDD

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Alumnos")
class Alumnos (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nombre: String = "",
    var apellidos: String = "",
    var curso: String = ""

)