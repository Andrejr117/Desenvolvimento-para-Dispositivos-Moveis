package com.example.myapplication.Class

import com.example.myapplication.Class.Jogador

data class Sala(

    var IDsala: Int,
    var Lista: ArrayList<Jogador>

) {

    private var contadorCodigoSala = 0

    private fun gerarCodigoSala(): String {
        contadorCodigoSala++
        return "SALA-$contadorCodigoSala"

    }

    companion object {
        fun add(it: Jogador) {

        }
    }


}