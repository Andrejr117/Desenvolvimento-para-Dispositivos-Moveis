package com.example.myapplication.Class

import com.example.myapplication.Class.Jogador

data class Sala(
    val salaId: Long? = null,
    val numeroSala: Int,
    val equipe1: String?,
    val equipe2: String?,
    val jogadores: MutableList<Jogador> = mutableListOf()
)
