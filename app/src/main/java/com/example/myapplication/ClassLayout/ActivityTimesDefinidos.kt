package com.example.myapplication.ClassLayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityTimesDefinidosBinding
import java.util.*

class ActivityTimesDefinidos : AppCompatActivity() {

    private lateinit var binding: ActivityTimesDefinidosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimesDefinidosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        // Recuperar os times passados como extras da Intent
        val time1 = intent.getStringArrayExtra("time1") ?: emptyArray()
        val time2 = intent.getStringArrayExtra("time2") ?: emptyArray()

        // Exibir os times na tela
        binding.textTime1.text = time1.joinToString(", ") // Converter o array em uma String separada por vírgulas
        binding.textTime2.text = time2.joinToString(", ") // Converter o array em uma String separada por vírgulas
    }


}



//Neste exemplo, o código simula um banco de dados com jogadores cadastrados na forma de uma
//lista de objetos Jogador. O usuário é solicitado a digitar o número de jogadores que deseja sortear.
//Em seguida, a função sortearJogadores é chamada para realizar o sorteio de forma aleatória.
//Os jogadores sorteados são exibidos no console.
//
//Tenha em mente que este código é apenas um exemplo simplificado e não lida com casos de banco de dados reais.
//Ele serve para ilustrar o conceito básico de como realizar um sorteio de jogadores a partir
//de um banco de dados simulado.
