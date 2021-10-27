package com.miempresa.tarea_lab10
import  com.orm.dsl.Table
import java.io.Serializable

@Table
data class Usuario(
    var id: Long? = null,
    var foto: String? = null,
    var nombres: String? = null,
    var correo: String? =null,
    var telefono: String? =null,
    var observaciones: String? = null): Serializable {
    constructor(foto: String?,nombres: String?,correo: String?,telefono: String?,observaciones: String?) : this() {
        this.foto = foto
        this.nombres = nombres
        this.correo = correo
        this.telefono = telefono
        this.observaciones = observaciones
    }
}