package com.heltonbustos.ejemplochiptooglebutton.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.heltonbustos.ejemplochiptooglebutton.R
import java.util.ArrayList

class AdaptadorDatos(var context: Context, var listDatos: ArrayList<LenguajesModelo>) :
    RecyclerView.Adapter<AdaptadorDatos.ViewHolderDatos>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDatos {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout, null, false)
        return ViewHolderDatos(view)
    }

    override fun onBindViewHolder(holder: ViewHolderDatos, position: Int) {
        holder.id.text = listDatos[position].id.toString()
        holder.lenguaje.text = listDatos[position].lenguaje
    }

    override fun getItemCount(): Int {
        return listDatos.size
    }

    inner class ViewHolderDatos(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView
        var lenguaje: TextView

        init {
            id = itemView.findViewById(R.id.txtId)
            lenguaje = itemView.findViewById(R.id.txtLenguaje)
        }

    }


}