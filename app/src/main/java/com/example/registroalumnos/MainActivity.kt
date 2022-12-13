package com.example.registroalumnos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.listacompra.R
import com.example.listacompra.databinding.ActivityMainBinding
import com.example.registroalumnos.BBDD.Alumnos
import com.example.registroalumnos.BBDD.AlumnosApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    companion object{
        var actividadActual = 0;
    }

    lateinit var ListaAlumnos: MutableList<Alumnos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ListaAlumnos = ArrayList()

        binding.BAnadir.setOnClickListener{
            anadirAlumno(Alumnos(nombre = binding.AnadirNombre.text.toString(), apellidos = binding.AnadirApellido.text.toString(), curso = binding.AnadirCurso.text.toString()))
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater =menuInflater
        inflater.inflate(R.menu.menu_principal, menu)
        for (i in 0 until menu.size()){
            if (i == actividadActual) menu.getItem(i).isEnabled = false
            else menu.getItem(i).isEnabled = true
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.AnadirAlumno -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                actividadActual = 0;
                startActivity(intent)
                true
            }

            R.id.ActualizarCurso -> {
                val intent = Intent(this, UpdateActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                actividadActual = 1;
                startActivity(intent)
                true
            }
            R.id.EliminarAlumno -> {
                val intent = Intent(this, DeleteActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                actividadActual = 2;
                startActivity(intent)
                true
            }


            else -> super.onOptionsItemSelected(item)
        }
    }

    fun anadirAlumno(alumno:Alumnos){
        CoroutineScope(Dispatchers.IO).launch {
            val id = AlumnosApp.database.alumnosDao().addAlumno(alumno)

            val recoveryAlumno = AlumnosApp.database.alumnosDao().getAlumnoId(id)
            runOnUiThread{
                ListaAlumnos.add(recoveryAlumno)
            }
        }

    }





}