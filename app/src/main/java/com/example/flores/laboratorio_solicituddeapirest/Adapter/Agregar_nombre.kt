package com.example.flores.laboratorio_solicituddeapirest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flores.laboratorio_solicituddeapirest.R
import java.io.Serializable


data class Nombres(val nombre: String):Serializable

class Agregar_nombre(private val LC: MutableList<Nombres>):
        RecyclerView.Adapter<Agregar_nombre.ProductoViewHolder>(){

        var selectedPosition: Int = RecyclerView.NO_POSITION

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
                val View = LayoutInflater.from(parent.context).inflate(R.layout.agregado, parent, false)
                return ProductoViewHolder(View)

        }
        override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
                val item = LC[position]
                holder.bin(item)
        }
        override fun getItemCount(): Int {
                return LC.size
        }

        inner class ProductoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

                private val textView: TextView = itemView.findViewById(R.id.textView)

                fun bin(producto: Nombres){
                        textView.text = "nombre_persona"+producto.nombre

                }
        }

}