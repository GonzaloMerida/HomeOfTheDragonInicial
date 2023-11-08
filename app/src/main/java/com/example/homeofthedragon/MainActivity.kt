package com.example.homeofthedragon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    //declaración de constantes
    companion object {
        const val NAME = "NAME"
        const val MAIN = "Main"
        const val LIFECLICLE = "LifeCycle"
    }

    //declaración de views.
    private lateinit var btnAnswer : Button
    private lateinit var etName : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //inicialización de views.
        btnAnswer = findViewById<Button>(R.id.btnAnswer)
        etName = findViewById<EditText>(R.id.etName)

        //Evento de escucha del botón.
        // Se toma el valor del cuadro de texto y se pasa a la función que abre la nueva activity.
        btnAnswer.setOnClickListener {
            val name = etName.text.toString()
            if (name.isEmpty()){
                //Muestra mensaje de error mediante el snackbar y oculta el teclado si el usuario
                //no introduce nada
                showErrorSnackbar(etName, getString(R.string.errorSnackbar))
            }else {
                Log.d(MAIN, "Botón pulsado. El nombre introducido es $name")
                goToElection(name)
            }
        }
    }

    private fun goToElection(name : String) {
        //Creamos el intent e incorporamos el nombre introducido.
        val intent = Intent(this@MainActivity,ElectionActivity::class.java)
        intent.putExtra(NAME,name)

        startActivity(intent)
    }

    /**
     * El parámetro view es la vista donde se mostrará el snackBar
     * El parámetro message, es un string con el mensaje de error que se le mostrará en el
     * snackBar
     */
    private fun showErrorSnackbar(view: View, message: String) {
        //creación del objeto Snackbar indicando la vista en la que se mostrará
        //el mensaje que mostrará y la duración del snackBar
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        //muestra el snackbar
        snackbar.show()

        // Ocultar el teclado virtual
        //obtenemos un objeto del teclado
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //ocultar el teclado de la ventana en la que se encuentre y el 0 indica que no se debe
        //forzar cambios en el teclado
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(LIFECLICLE,getString(R.string.activity_restarted))
    }

    override fun onStart() {
        super.onStart()
        Log.d(LIFECLICLE,getString(R.string.activity_started))
    }

    override fun onResume() {
        super.onResume()
        Log.d(LIFECLICLE,getString(R.string.activity_resumed))
    }

    override fun onPause() {
        super.onPause()
        Log.d(LIFECLICLE,getString(R.string.activity_paused))
    }

    override fun onStop() {
        super.onStop()
        Log.d(LIFECLICLE,getString(R.string.activity_stopped))
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LIFECLICLE,getString(R.string.activity_destroyed))
    }

}

