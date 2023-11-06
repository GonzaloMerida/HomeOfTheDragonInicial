package com.example.homeofthedragon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ElectionActivity : AppCompatActivity() {

    private lateinit var tvGreetingElection : TextView

    private lateinit var tvInfoElection : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_election)

        //inicialización del TextView.
        tvGreetingElection = findViewById<TextView>(R.id.tvGreetingElection)
        //tomamos el valor del parámetro String que llega con el intent.
        val name = intent.getStringExtra(MainActivity.NAME)
        tvGreetingElection.text = getString(R.string.greeting_election,name)

        tvInfoElection = findViewById<TextView>(R.id.tvGreetingElection)


    }
}