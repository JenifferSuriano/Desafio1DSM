import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadorabasica.R

class MainActivity : AppCompatActivity() {

    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var buttonAdd: Button
    private lateinit var buttonSubtract: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonDivide: Button
    private lateinit var textViewResult: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonSubtract = findViewById(R.id.buttonSubtract)
        buttonMultiply = findViewById(R.id.buttonMultiply)
        buttonDivide = findViewById(R.id.buttonDivide)
        textViewResult = findViewById(R.id.textViewResult)


        buttonAdd.setOnClickListener { calculate(Operation.ADD) }
        buttonSubtract.setOnClickListener { calculate(Operation.SUBTRACT) }
        buttonMultiply.setOnClickListener { calculate(Operation.MULTIPLY) }
        buttonDivide.setOnClickListener { calculate(Operation.DIVIDE) }
    }


    private fun calculate(operation: Operation) {

        val num1 = getInputNumber(input1)
        val num2 = getInputNumber(input2)


        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Por favor, ingresa ambos números", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (operation) {
            Operation.ADD -> {
                // Suma
                num1 + num2
            }
            Operation.SUBTRACT -> {
                // Resta
                num1 - num2
            }
            Operation.MULTIPLY -> {
                // Multiplicación
                num1 * num2
            }
            Operation.DIVIDE -> {
                //  división
                if (num2 == 0.0) {
                    Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show()
                    return
                }
                // División
                num1 / num2
            }
        }


        textViewResult.text = getString(R.string.result) + String.format("%.2f", result)
    }


    private fun getInputNumber(input: EditText): Double? {
        return try {

            input.text.toString().toDouble()
        } catch (e: NumberFormatException) {

            null
        }
    }


    enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}
