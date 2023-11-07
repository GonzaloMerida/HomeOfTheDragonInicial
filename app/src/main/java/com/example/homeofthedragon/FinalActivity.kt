package com.example.homeofthedragon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FinalActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView
    private lateinit var bttnIndex: Button
    private lateinit var bttnBack: Button
    private lateinit var bttnExit: Button


    companion object{
        const val RESULT_INCOME_ELECTION = "result"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        // Inicialización de vistas
        tvResult = findViewById(R.id.tvResult)
        bttnIndex = findViewById(R.id.bttnIndex)
        bttnBack = findViewById(R.id.bttnBack)
        bttnExit = findViewById(R.id.bttnExit)

        //recojo el valor del intent pasado desde ElectionActivity y lo guardo
        //en la variable result
        val resultElection = intent.getStringExtra(RESULT_INCOME_ELECTION)

        // Actualiza el texto en función del resultado
        tvResult.text = when (resultElection) {
            "neither" -> getString(R.string.result_neither)
            "raenira" -> getString(R.string.result_raenira)
            "aegon" -> getString(R.string.result_aegon)
            "both" -> getString(R.string.result_both)
            else -> ""
        }

        // Eventos de los botones
        bttnIndex.setOnClickListener {
            // Regresar a la actividad principal
            val intent = Intent(this@FinalActivity, MainActivity::class.java)
            startActivity(intent)
        }

        bttnBack.setOnClickListener {
            // Regresar a la actividad ElectionActivity
            val intent = Intent(this@FinalActivity, ElectionActivity::class.java)
            startActivity(intent)
        }

        bttnExit.setOnClickListener {
            // Cerrar completamente la aplicación
            finishAffinity()
        }
    }
}