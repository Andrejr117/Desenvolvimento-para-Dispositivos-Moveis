package com.example.myapplication

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
    }






    //ver se vai usar algum cód
    data class Jogador(val id: Int, val nome: String)

    fun main() {
        // Simulação de banco de dados com jogadores cadastrados
        val jogadores = listOf(
            Jogador(1, "João"),
            Jogador(2, "Maria"),
            Jogador(3, "Pedro"),
            Jogador(4, "Ana")
        )

        val scanner = Scanner(System.`in`)

        println("Bem-vindo(a) ao sorteio de jogadores!")
        println("Digite o número de jogadores que deseja sortear:")
        val numeroSorteados = scanner.nextInt()

        if (numeroSorteados > jogadores.size) {
            println("O número de jogadores a serem sorteados excede o número de jogadores cadastrados.")
        } else {
            val jogadoresSorteados = sortearJogadores(jogadores, numeroSorteados)
            println("Os jogadores sorteados são:")
            for (jogador in jogadoresSorteados) {
                println("${jogador.nome} (ID: ${jogador.id})")
            }
        }
    }

    fun sortearJogadores(jogadores: List<Jogador>, numeroSorteados: Int): List<Jogador> {
        val jogadoresSorteados = mutableListOf<Jogador>()
        val numerosSorteados = mutableSetOf<Int>()
        val random = Random()

        while (jogadoresSorteados.size < numeroSorteados) {
            val indiceSorteado = random.nextInt(jogadores.size)
            if (indiceSorteado !in numerosSorteados) {
                numerosSorteados.add(indiceSorteado)
                jogadoresSorteados.add(jogadores[indiceSorteado])
            }
        }

        return jogadoresSorteados
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
