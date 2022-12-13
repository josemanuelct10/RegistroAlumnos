package com.example.registroalumnos.BBDD

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AlumnosDAO {

    @Query("SELECT * FROM Alumnos WHERE id like :id")
    fun getAlumnoId(id: Long): Alumnos

    @Query("SELECT * FROM Alumnos WHERE nombre like :nombre")
    fun getAlumnoNombre(nombre: String): Alumnos

    @Insert
    fun addAlumno(alumno: Alumnos): Long

    @Update
    fun updateCurso(taskEnity: Alumnos): Int

    @Delete
    fun deleteAlumno(alumno: Alumnos): Int

}