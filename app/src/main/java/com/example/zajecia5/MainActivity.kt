package com.example.zajecia5

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import kotlin.math.cos

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val myRecyclerView = findViewById<RecyclerView>(R.id.myRecycleView)
        val myAdapter = MyAdapter(mutableListOf()) { animal ->
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("MY_EXTRA", animal)
            startActivity(intent)
        }

        myRecyclerView.adapter = myAdapter
        myRecyclerView.layoutManager = LinearLayoutManager(this)



        CoroutineScope(Dispatchers.Main).launch {
            val response =   fetchPokemonList()

            val myData = listOf(
                MyAnimal("Pokemon", response, "Woof woof"),
            )

            myAdapter.updateList(myData)
        }


    }

    suspend fun fetchPokemonList() : String {
        return withContext(Dispatchers.IO) {
            try {
                val connection = URL("https://pokeapi.co/api/v2/pokemon").openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connect()

                val response = connection.inputStream.bufferedReader().readText()

                connection.disconnect()

                response
            } catch (e: Exception) {
                e.printStackTrace()
                "Error"
            }
        }
    }
}