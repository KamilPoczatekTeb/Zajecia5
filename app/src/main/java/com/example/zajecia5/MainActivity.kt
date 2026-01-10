package com.example.zajecia5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

        val myData = listOf(
            MyAnimal("Dog", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque neque ligula, condimentum at libero eget, vehicula suscipit ex. Nam porttitor.", "Woof woof"),
            MyAnimal("Cat", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque neque ligula, condimentum at libero eget, vehicula suscipit ex. Nam porttitor.", "Meow meow"),
            MyAnimal("Cow", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque neque ligula, condimentum at libero eget, vehicula suscipit ex. Nam porttitor.", "Moo mooo")
            )

        val myRecyclerView = findViewById<RecyclerView>(R.id.myRecycleView)
        myRecyclerView.adapter = MyAdapter(myData) { animal ->
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("MY_EXTRA", animal)
            startActivity(intent)
        }
        myRecyclerView.layoutManager = LinearLayoutManager(this)


    }
}