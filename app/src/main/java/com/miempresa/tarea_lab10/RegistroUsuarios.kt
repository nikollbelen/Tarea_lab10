package com.miempresa.tarea_lab10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_registro_usuarios.*
import java.util.*

class RegistroUsuarios : AppCompatActivity() {

    var edita: Boolean = false
    var id: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuarios)

        textViewimagen.isVisible = false

        btnCargarImg.setOnClickListener {
            val randomInt = Random().nextInt(6) + 1

            val drawableResource = when (randomInt) {
                1 -> R.drawable.batman
                2 -> R.drawable.lego
                3 -> R.drawable.spiderman
                4 -> R.drawable.superman
                5 -> R.drawable.woman
                else -> R.drawable.deadpool
            }

            txtFoto.setImageResource(drawableResource)

            textViewimagen.text = drawableResource.toString()
        }

        btnRegistrar.setOnClickListener(){
            var foto = textViewimagen.text.toString()
            var nombres = txtNombres.text.toString()
            var correo = txtCorreo.text.toString()
            var telefono = txtTelefono.text.toString()
            var observaciones = txtObservaciones.text.toString()

            if (foto.isEmpty() || nombres.isEmpty() || correo.isEmpty() || telefono.isEmpty() || observaciones.isEmpty()){
                Toast.makeText(this,"Algunos campos estan vacios", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (edita) {
                var usuariorepo = UsuarioRepositorio()
                usuariorepo.actualizar(id,foto, nombres, correo, telefono, observaciones)
            }else{
                var usuariorepo = UsuarioRepositorio()
                usuariorepo.crear(foto, nombres, correo, telefono, observaciones)
            }
            finish()

            //var datoGuardado = usuariorepo.listar().size
            //Toast.makeText(this,"Datos guardados:\n" + datoGuardado , Toast.LENGTH_LONG).show()
        }
        var recibidos:Bundle? = intent.extras
        if (recibidos != null){
            var  usuario = recibidos?.get("usuario") as Usuario
            edita = true
            id = usuario.id!!
            textViewimagen.setText(usuario.foto)
            txtNombres.setText(usuario.nombres)
            txtCorreo.setText(usuario.correo)
            txtTelefono.setText(usuario.telefono)
            txtObservaciones.setText(usuario.observaciones)
        }
    }
}