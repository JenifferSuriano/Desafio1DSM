package com.example.desafio01ejer02

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

    //Declaración de variables
    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var btnCalcular: Button
    private lateinit var txRent: TextView
    private lateinit var txAFP: TextView
    private lateinit var txISSS: TextView
    private lateinit var txNeto: TextView
    private lateinit var txName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Inicialización de elementos de interfaz
        input1 = findViewById(R.id.nombreEmp)
        input2 = findViewById(R.id.salarioEmp)
        btnCalcular = findViewById(R.id.btnCalculate)
        txRent = findViewById(R.id.rent)
        txAFP = findViewById(R.id.afp)
        txISSS = findViewById(R.id.isss)
        txNeto = findViewById(R.id.neto)
        txName = findViewById(R.id.nombre)

        //Listener de button Calcular
        btnCalcular.setOnClickListener{calculate()}

    }

    private fun calculate(){
        val ip1T = input1.text.toString()
        val ip2T = input2.text.toString()

        if (ip1T.isEmpty() || ip2T.isEmpty() || ip2T.toDouble() == 0.00 ){
            Toast.makeText(this, "Hay datos no validos o el salario es 0!",
                Toast.LENGTH_SHORT).show()
            return
        }

        val salary = ip2T.toDouble()
        var rRent = 0.00
        var rAFP = 0.00
        var rISSS = 0.00
        var rNeto = 0.00
        var nombreEmpleado = ip1T

        //Validando renta
        if (salary >= 0.01 && salary <= 472.00 ){
            rRent = 0.00
        }else if (salary >= 472.01 && salary <= 895.24){
            rRent = (salary * 0.10)
        }else if (salary >= 895.25 && salary <= 2038.10){
            rRent = (salary * 0.20)
        }else{
            rRent = (salary * 0.30)
        }

        //Calculando
        rAFP = salary * 0.0725
        rISSS = salary * 0.03
        rNeto = salary - rRent - rAFP - rISSS

        if (rRent == 0.00){
            txRent.text = getString(R.string.renta) + " " + getString(R.string.notComply)
        }else{
            txRent.text = getString(R.string.renta) + " " + String.format("%.2f", rRent)
        }

        txName.text = getString(R.string.nameResult) + " " + nombreEmpleado
        txAFP.text = getString(R.string.afp) + " " + String.format("%.2f", rAFP)
        txISSS.text = getString(R.string.isss) + " " + String.format("%.2f", rISSS)
        txNeto.text = getString(R.string.neto_salary) + " " + String.format("%.2f", rNeto)
    }

//    private fun twoDigits(value: Double): String {
//        val format: DecimalFormat = DecimalFormat()
//        format.setMaximumFractionDigits(2) //Define 2 decimales.
//        return format.format(value)
//    }
}