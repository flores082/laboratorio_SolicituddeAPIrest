package com.example.flores.laboratorio_solicituddeapirest

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flores.laboratorio_solicituddeapirest.Adapter.Agregar_nombre
//import com.example.flores.laboratorio_solicituddeapirest.Dao.NombreDao
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.flores.laboratorio_solicituddeapirest.Adapter.NombreEntity


class MainActivity : AppCompatActivity() {


    private var nombresList = mutableListOf<NombreEntity>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Agregar_nombre
    //private lateinit var nombreDao: NombreDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = Agregar_nombre(nombresList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val agregarButton = findViewById<Button>(R.id.button)
        agregarButton.setOnClickListener {
            // Ejemplo: Agregar un nuevo elemento a la lista
            val nuevoNombre = "Nuevo Nombre - ${nombresList.size + 1}"
            val nombreEntity = NombreEntity(nombre = nuevoNombre)
            nombresList.add(nombreEntity)
            adapter.notifyItemInserted(nombresList.size - 1)

            GlobalScope.launch(Dispatchers.IO) {
                nombreEntity
            }
        }
        // Configurar la base de datos Room
        //val db = AppDatabase.getDatabase(applicationContext)
        //nombreDao = db.nombreDao()

        // Ejemplo: Realizar la conexión a la API en un hilo separado
        MyAsyncTask().execute()
    }


    private inner class MyAsyncTask : AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg params: Void): String? {
            // URL de ejemplo (sustituye por la URL de tu API)
            val urlString = "https://jsonplaceholder.typicode.com/todos"

            // Inicia la conexión HTTP
            var connection: HttpURLConnection? = null
            var reader: BufferedReader? = null

            try {
                val url = URL(urlString)
                connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"

                // Lee la respuesta
                val inputStream = connection.inputStream
                reader = BufferedReader(InputStreamReader(inputStream))
                val stringBuilder = StringBuilder()
                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    stringBuilder.append(line).append("\n")
                }

                return stringBuilder.toString()
            } catch (e: Exception) {
                e.printStackTrace()
                // Manejar la excepción según tus necesidades
            } finally {
                // Cierra los recursos
                connection?.disconnect()
                try {
                    reader?.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            return null
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            // Manejar el resultado aquí, por ejemplo, mostrarlo en un TextView
            println("Respuesta de la API:\n$result")

            // Procesar el resultado JSON y agregar a la lista
            result?.let {
                parseJsonAndPopulateList(it)
                adapter.notifyDataSetChanged()
            }
        }

        private fun parseJsonAndPopulateList(jsonString: String) {
            nombresList.clear()

            val jsonArray = JSONArray(jsonString)
            for (i in 0 until jsonArray.length()) {
                val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                val nombre = jsonObject.getString("title")
                val id = jsonObject.getInt("id")
                // Crear una instancia de NombreEntity con el id y nombre
                val nombreEntity = NombreEntity(id.toLong(), "$nombre - $id")
                nombresList.add(nombreEntity)
            }
        }
    }

}
