package com.example.flores.laboratorio_solicituddeapirest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.flores.laboratorio_solicituddeapirest.Adapter.Agregar_nombre
import com.example.flores.laboratorio_solicituddeapirest.database.AppDatabase
import com.example.flores.laboratorio_solicituddeapirest.entity.Product
import com.example.flores.laboratorio_solicituddeapirest.entity.User
import com.example.flores.laboratorio_solicituddeapirest.entity.UserWithProducts

class MainActivity : AppCompatActivity() {

    private lateinit var nombresList : MutableList<User> // Lista de nombres
    private lateinit var agregarNombreAdapter: Agregar_nombre
    private lateinit var listViewUsers : ListView
    private lateinit var adapter : ArrayAdapter<String>
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()


        val list : List<com.example.flores.laboratorio_solicituddeapirest.entity.User> = db.userDao().getAll()
        nombresList = list.toMutableList()
        //adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list.map { it.firstName })
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombresList.map { it.firstName })
        Toast.makeText(this,"Hi: "+nombresList.size.toString(), Toast.LENGTH_LONG).show()
        listViewUsers.adapter = adapter
        list.size

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = agregarNombreAdapter

        val agregar = findViewById<Button>(R.id.button)

        agregar.setOnClickListener {
            val position = agregarNombreAdapter.selectedPosition
            Toast.makeText(this,"User:"+position.toString(), Toast.LENGTH_LONG).show()
            var listProduct : MutableList<Product>
            listProduct = mutableListOf(
                Product(0,nombresList[position].uid,"Coca Cola")
            )
            UserWithProducts(
                nombresList[position],
                listProduct
            )
            db.productDao().insertAll(listProduct[0])
            refreshFromDatabase()
        }

        registerForContextMenu(listViewUsers)
    }

    public fun refreshFromDatabase(){
        val list : List<User> = db.userDao().getAll()
        val listUserWith : List<UserWithProducts> = db.userDao().getUsersWithPlaylists()
        listUserWith.forEach {
            Log.i("UserRoomExampleActivity","Data user:"+it.user.firstName)
            Log.i("UserRoomExampleActivity","Data products of user:"+it.products.toString())
        }
        Toast.makeText(this,"Hi: "+list.size.toString(), Toast.LENGTH_LONG).show()
        nombresList = list.toMutableList()
        Toast.makeText(this,"Hi: "+nombresList.size.toString(), Toast.LENGTH_LONG).show()
        listViewUsers.invalidate()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombresList.map { it.firstName })
        listViewUsers.adapter = adapter
    }

}
