package com.example.calculadora_conversion

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora_conversion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    private val unidades = arrayOf("Metro", "Pulgada", "Kilogramo", "Libra", "Celsius", "Fahrenheit")
    private val factores = arrayOf(1.0, 39.37, 1.0, 2.20462, 1.0, 33.8)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, unidades)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        mainBinding.spinnerUnidadOrigen.adapter = adapter
        mainBinding.spinnerUnidadDestino.adapter = adapter

        mainBinding.buttonConvertir.setOnClickListener {
            convertirUnidades()
        }
    }

    private fun convertirUnidades() {
        val valorAConvertir = mainBinding.editTextValor.text.toString()
        if (valorAConvertir.isEmpty()){
            Toast.makeText(this,"Ingrese el valor a convertir", Toast.LENGTH_SHORT).show()
        }
       else{
            val unidadOrigen = mainBinding.spinnerUnidadOrigen.selectedItemPosition
            val unidadDestino = mainBinding.spinnerUnidadDestino.selectedItemPosition

            val resultado = valorAConvertir.toDouble() * factores[unidadOrigen] / factores[unidadDestino]

            mainBinding.textViewResultado.text = "$valorAConvertir ${unidades[unidadOrigen]} es igual a $resultado ${unidades[unidadDestino]}"
       }
    }
}
