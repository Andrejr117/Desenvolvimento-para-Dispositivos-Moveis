package com.example.myapplication.networkconection

import com.example.myapplication.Class.AlterarSenhaResponse
import com.example.myapplication.Class.Jogador
import com.example.myapplication.Class.LoginResponse
import com.example.myapplication.Class.Sala
import retrofit2.Call
import retrofit2.http.*

interface MyApi {


    @FormUrlEncoded
    @POST("login")
    fun realizarLogin(
        @Field("email") email: String,
        @Field("senha") senha: String
    ): Call<LoginResponse>

    @GET("jogadores/{jogadorId}")
    fun obterInformacoesJogador(): Call<Jogador>

    @FormUrlEncoded
    @POST("alterarSenha")
    fun alterarSenha(
        @Field("email") email: String,
        @Field("novaSenha") novaSenha: Int
    ): Call<AlterarSenhaResponse>


    @POST("logout")
    fun deslogarUsuario(): Call<Void>


    @POST("criar_times")
    fun criarTimes(): Call<Sala>

    @POST("jogadores")
    fun cadastrarJogador(@Body jogador: Jogador): Call<Void>

}





// interface que define as chamadas de API que deseja fazer.

//    @GET("")
//    fun ListNomeFuncao(@Path("") user: String?): Call<List<Funcao>>

//exemplo:
//fun getPosts(): List<Post>