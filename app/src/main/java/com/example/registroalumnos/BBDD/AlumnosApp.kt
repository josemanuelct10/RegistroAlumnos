package com.example.registroalumnos.BBDD

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase

class AlumnosApp: Application() {
    companion object{
        lateinit var database: DBAlumnos
    }

    override fun onCreate() {
        super.onCreate()
        AlumnosApp.database = Room.databaseBuilder(this, DBAlumnos::class.java, "DBAlumnos").build()
    }
}