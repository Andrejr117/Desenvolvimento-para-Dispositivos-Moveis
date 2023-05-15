package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivitySalaSorteioBinding

class ActivitySalaSorteio : AppCompatActivity() {

    lateinit var binding: ActivitySalaSorteioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalaSorteioBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val Jogador = mutableListOf<Jogador>()

        fun addJogador(playerName: String) {
            println("O jogador $playerName entrou no lobby.")
        }

        fun removePlayer(playerName: String) {
            println("O jogador $playerName saiu do lobby.")
        }

        fun listPlayers() {
            println("Jogadores no lobby:")
            for (jogador in Jogador) {
                println(jogador)
            }
        }
    }



}





}