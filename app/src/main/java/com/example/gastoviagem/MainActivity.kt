package com.example.gastoviagem

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gastoviagem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalcular.setOnClickListener(this)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_calcular) {
            calculate()

        }
    }

    private fun isValid(): Boolean {
        return (
            binding.editDistancia.text.toString() != "" &&
            binding.editPreco.text.toString() != "" &&
            binding.editAutonomia.text.toString() != "" &&
            binding.editAutonomia.text.toString().toFloat() != 0f
        )
    }

    private fun calculate() {

        if (isValid()) {

            var distancia = binding.editDistancia.text.toString().toInt()
            var preco = binding.editPreco.text.toString().toFloat()
            var autonomia = binding.editAutonomia.text.toString().toFloat()

            var totalValue = (distancia * preco) / autonomia

            binding.textValorTotal.text = "R$ ${"%.2f".format(totalValue)}"

            binding.editDistancia.text.clear()
            binding.editPreco.text.clear()
            binding.editAutonomia.text.clear()
        } else {
            Toast.makeText(this, R.string.validacao_de_campos, Toast.LENGTH_SHORT).show()
        }
    }
}
