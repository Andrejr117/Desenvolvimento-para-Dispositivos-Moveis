package com.example.myapplication.networkconection

import com.example.myapplication.Class.*
import retrofit2.Call
import retrofit2.Response
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

    @GET("jogadores")
    suspend fun obterJogadores(): List<Jogador>

    @FormUrlEncoded
    @POST("alterarSenha")
    fun alterarSenha(
        @Field("email") email: String,
        @Field("novaSenha") novaSenha: Int
    ): Call<AlterarSenhaResponse>



    @POST("sala")
    fun criarSala(): Call<Sala>


    @POST("logout")
    fun deslogarUsuario(): Call<Void>


    @GET("verificarCodigo")
    fun verificarCodigo(@Query("codigo") codigo: String): Call<Response<VerificarCodigoResponse>>

    @POST("criar_times")
    fun criarTimes(): Call<Sala>


    @POST("jogador")
    fun cadastrarJogador(@Body jogador: Jogador): Call<Void>

}





// interface que define as chamadas de API que deseja fazer.

//    @GET("")
//    fun ListNomeFuncao(@Path("") user: String?): Call<List<Funcao>>

//exemplo:
//fun getPosts(): List<Post>