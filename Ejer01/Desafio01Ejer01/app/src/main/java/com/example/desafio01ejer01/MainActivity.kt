package com.example.desafio01ejer01

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var score1: EditText
    private lateinit var score2: EditText
    private lateinit var score3: EditText
    private lateinit var score4: EditText
    private lateinit var score5: EditText
    private lateinit var result: TextView
    private lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Inicializacion de variables
        name = findViewById(R.id.nameStudent)
        score1 = findViewById(R.id.n1)
        score2 = findViewById(R.id.n2)
        score3 = findViewById(R.id.n3)
        score4 = findViewById(R.id.n4)
        score5 = findViewById(R.id.n5)
        result = findViewById(R.id.resultText)
        btn = findViewById(R.id.btnCalcular)


        btn.setOnClickListener{calculate()}
    }

    private fun calculate(){
        val nameText = name.text.toString()
        val score1Text = score1.text.toString()
        val score2Text = score2.text.toString()
        val score3Text = score3.text.toString()
        val score4Text = score4.text.toString()
        val score5Text = score5.text.toString()

        if (nameText.isEmpty() || score1Text.isEmpty()
            || score2Text.isEmpty() || score3Text.isEmpty() || score4Text.isEmpty()
            || score5Text.isEmpty()){
            Toast.makeText(this, "Los datos no pueden estar vacios",
                Toast.LENGTH_SHORT).show()
            return
        }

        val n1 = score1Text.toDouble()
        val n2 = score2Text.toDouble()
        val n3 = score3Text.toDouble()
        val n4 = score4Text.toDouble()
        val n5 = score5Text.toDouble()

        if (n1 > 10 || n2 > 10 || n3 > 10 || n4 > 10 || n5 > 10) {
            Toast.makeText(
                this, "Las notas no pueden pasar del 10!",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val nota1 = n1 * 0.15
        val nota2 = n2 * 0.15
        val nota3 = n3 * 0.20
        val nota4 = n4 * 0.25
        val nota5 = n5 * 0.25
        val resultScore = nota1 + nota2 + nota3 + nota4 + nota5
        var aprep = ""

        if (resultScore >= 6){
            aprep = "Aprobado :)"
        }else{
            aprep = "Reprobado :("
        }

        result.text = "Hola " + nameText + " Tu nota final es: " + resultScore + " Has " + aprep
    }

}