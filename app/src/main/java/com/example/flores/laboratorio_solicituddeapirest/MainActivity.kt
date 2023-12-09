package com.example.flores.laboratorio_solicituddeapirest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flores.laboratorio_solicituddeapirest.Adapter.Agregar_nombre
import com.example.flores.laboratorio_solicituddeapirest.Adapter.Nombres


class MainActivity : AppCompatActivity() {


    private var Lista = mutableListOf<Nombres>()
    lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        initRecyclerView()

        val agregar = findViewById<Button>(R.id.button)

        agregar.setOnClickListener {
            val item=Nombres("Hola")
            Lista.add(item)
            recyclerView.adapter?.notifyDataSetChanged()
        }

    }

    private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Agregar_nombre(Lista)
    }

}
