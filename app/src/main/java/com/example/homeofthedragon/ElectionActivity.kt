package com.example.homeofthedragon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class ElectionActivity : AppCompatActivity() {

    private lateinit var tvGreetingElection : TextView

    private lateinit var tvInfoElection : TextView
    private lateinit var cbRaenira : CheckBox
    private lateinit var cbAegon : CheckBox
    private lateinit var bttnHincarRodilla : Button

    companion object {
        val RESULT_ELECTION = "result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_election)

        //inicialización del las vistas.
        tvGreetingElection = findViewById<TextView>(R.id.tvGreetingElection)
        tvInfoElection = findViewById(R.id.tvInfoEleccion)
        cbRaenira = findViewById(R.id.cbRaenira)
        cbAegon = findViewById(R.id.cbAegon)
        bttnHincarRodilla = findViewById(R.id.bttnHincarRodilla)
        //tomamos el valor del parámetro String que llega con el intent.
        val name = intent.getStringExtra(MainActivity.NAME)
        tvGreetingElection.text = getString(R.string.greeting_election,name)


        //evento del botón hincar la rodilla
        bttnHincarRodilla.setOnClickListener {
            val result = getResultFromCheckboxes(cbRaenira.isChecked, cbAegon.isChecked)
            // Lanza la actividad FinalActivity con el resultado
            val intent = Intent(this@ElectionActivity, FinalActivity::class.java)
            intent.putExtra(RESULT_ELECTION, result)
            startActivity(intent)
        }


        // Eventos de cambio en los checkboxes
        cbRaenira.setOnCheckedChangeListener { buttonView, isChecked ->
            updateInfoText()
        }

        cbAegon.setOnCheckedChangeListener { buttonView, isChecked ->
            updateInfoText()
        }

        // Inicializa el texto de información
        updateInfoText()
    }

    /**
     * Actualiza lo que se muestra en el tvInfoElection según los checkboxes marcados
     */
    private fun updateInfoText() {
        val result = getResultFromCheckboxes(cbRaenira.isChecked, cbAegon.isChecked)
        tvInfoElection.text = when (result) {
            "neither" -> getString(R.string.info_neither)
            "raenira" -> getString(R.string.info_raenira)
            "aegon" -> getString(R.string.info_aegon)
            "both" -> getString(R.string.info_both)
            else -> ""
        }
    }

    /**
     * Método que permitirá indicar a otros métodos qué checkboxes están marcados
     */
    private fun getResultFromCheckboxes(isRaeniraChecked: Boolean, isAegonChecked: Boolean): String {
        return when {
            isRaeniraChecked && isAegonChecked -> "both"
            isRaeniraChecked -> "raenira"
            isAegonChecked -> "aegon"
            else -> "neither"
        }
    }
}