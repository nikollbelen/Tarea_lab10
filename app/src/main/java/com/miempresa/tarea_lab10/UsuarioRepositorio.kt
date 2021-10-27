package com.miempresa.tarea_lab10

import  com.orm.SugarRecord

class UsuarioRepositorio {
    fun crear(foto: String,nombres: String, correo: String, telefono: String, observaciones: String) {
        var usuario = Usuario(foto, nombres, correo, telefono, observaciones)
        SugarRecord.save(usuario)
    }

    fun listar(): List<Usuario> {
        var usuarios = SugarRecord.listAll(Usuario::class.java)
        return usuarios
    }

    fun borrar(id:Long){
        var  usuario: Usuario = SugarRecord.findById(Usuario::class.java, id)
        SugarRecord.delete(usuario)
    }

    fun leer(id:Long): Usuario{
        var  usuario: Usuario = SugarRecord.findById(Usuario::class.java, id)
        return usuario
    }

    fun actualizar(id:Long, foto: String?,nombres: String?,correo: String?,telefono: String?,observaciones: String?){
        var  usuario: Usuario = SugarRecord.findById(Usuario::class.java, id)
        usuario.foto = foto
        usuario.nombres = nombres
        usuario.correo = correo
        usuario.telefono = telefono
        usuario.observaciones = observaciones
        SugarRecord.save(usuario)
    }
}