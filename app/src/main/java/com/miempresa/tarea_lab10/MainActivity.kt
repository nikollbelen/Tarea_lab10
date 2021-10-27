package com.miempresa.tarea_lab10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lista.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        lista.layoutManager = LinearLayoutManager(this);

        // obtenemos lista de datos guardados en sugarORM
        var usuariorepo = UsuarioRepositorio()
        var listaUsuarios = usuariorepo.listar()

        val adapter = AdaptadorUsuarios(listaUsuarios as ArrayList<Usuario>)
        lista.adapter = adapter

        val boton1=findViewById<FloatingActionButton>(R.id.btnAgregar)
        boton1.setOnClickListener {
            val intento1 = Intent(this, RegistroUsuarios::class.java)
            startActivity(intento1)
        }
    }

    override fun onRestart() {
        super.onRestart()
        var usuariorepo = UsuarioRepositorio()
        var listaUsuarios = usuariorepo.listar()

        val adapter = AdaptadorUsuarios(listaUsuarios as ArrayList<Usuario>)
        lista.adapter = adapter
    }
}