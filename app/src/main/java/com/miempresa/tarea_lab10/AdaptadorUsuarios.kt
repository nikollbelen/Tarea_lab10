package com.miempresa.tarea_lab10

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class AdaptadorUsuarios(val ListaUsuarios:ArrayList<Usuario>): RecyclerView.Adapter<AdaptadorUsuarios.ViewHolder>() {

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val fImagen = itemView.findViewById<ImageView>(R.id.imgFoto)
        val fNombre = itemView.findViewById<TextView>(R.id.lblNombre)
        val fTelefono = itemView.findViewById<TextView>(R.id.lblTelefono);
        val fEliminar = itemView.findViewById<ImageButton>(R.id.btnEliminar);
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.vista_usuario, parent, false);
        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder: AdaptadorUsuarios.ViewHolder, position: Int) {
        val user = this.ListaUsuarios.get(position)
        var foto = ListaUsuarios[position].foto?.toInt()
        if (foto != null) {
            holder?.fImagen?.setImageResource(foto)
        }
        holder?.fNombre?.text= ListaUsuarios[position].nombres
        holder?.fTelefono?.text= ListaUsuarios[position].telefono
        holder?.fEliminar.setOnClickListener(){
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(holder?.itemView.context)

            // set message of alert dialog
            dialogBuilder.setMessage("¿Segura que quieres eliminar este usuario? ")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("Si", DialogInterface.OnClickListener {
                        dialog, id ->
                    var userrepo = UsuarioRepositorio()
                    userrepo.borrar(user.id!!)
                    this.ListaUsuarios.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position,itemCount)
                })
                // negative button text and action
                .setNegativeButton("Cancelar", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("Confirmación")
            // show alert dialog
            alert.show()
        }

        holder?.itemView.setOnClickListener(){
            var userrepo = UsuarioRepositorio()
            var usuario = userrepo.leer(user.id!!)

            var editarUsuario = Intent(holder?.itemView.context,RegistroUsuarios::class.java)
            editarUsuario.putExtra("usuario",usuario as Serializable)
            holder?.itemView.context.startActivity(editarUsuario)
        }
    }

    override fun getItemCount(): Int {
        return ListaUsuarios.size;
    }
}
