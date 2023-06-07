package com.example.myapplication.Class


data class Jogador(
    var jogadorid: Long = 0,
    var email: String,
    var nome: String,
    @Transient
    var senha: Int?,
    var endereco: Endereco,
    var nacionalidade: String,
    var posicao: String,
    var peDominante: String,
    var altura: Double,
    var sala: Unit? = null
//    @Transient
//    var equipe: String?
)
{


}