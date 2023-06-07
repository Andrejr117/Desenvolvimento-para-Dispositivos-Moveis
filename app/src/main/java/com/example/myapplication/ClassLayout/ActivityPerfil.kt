package com.example.myapplication.ClassLayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.Class.ApiManager
import com.example.myapplication.Class.Jogador
import com.example.myapplication.databinding.ActivityPerfilBinding
import com.example.myapplication.networkconection.MyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityPerfil : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        obterInformacoesJogador()

        binding.btLogout.setOnClickListener{
            deslogarUsuario()
        }

        }


    private fun obterInformacoesJogador() {

        val retrofit = ApiManager.getRetrofitInstance()
        val apiService = retrofit.create(MyApi::class.java)

        val call = apiService.obterInformacoesJogador()
        call.enqueue(object : Callback<Jogador> {
            override fun onResponse(call: Call<Jogador>, response: Response<Jogador>) {
                if (response.isSuccessful) {
                    val jogador: Jogador? = response.body()
                    if (jogador != null) {
                        exibirInformacoesJogador(jogador)
                    } else {
                        // Resposta inválida do servidor
                    }
                } else {
                    // Trate a resposta de erro
                }
            }

            override fun onFailure(call: Call<Jogador>, t: Throwable) {
                t.printStackTrace()
            // Trate o erro
            }
        })
    }

    private fun exibirInformacoesJogador( jogador: Jogador) {
        // Atualize os campos da tela de perfil com as informações do usuário

        binding.textNomeUsuario.text = jogador.nome
        binding.textPosicao.text = jogador.posicao
        binding.textAltura.text = jogador.altura.toString()
        binding.textNacionalidade.text = jogador.nacionalidade
        binding.textPeDominante.text = jogador.peDominante

    }




    private fun deslogarUsuario() {
        val retrofit = ApiManager.getRetrofitInstance()
        val apiService = retrofit.create(MyApi::class.java)

        val call = apiService.deslogarUsuario()
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Redirecionar o usuário para a tela de login
                    val intent = Intent(this@ActivityPerfil, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Tratar resposta de erro
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                t.printStackTrace()
            // Tratar erro de conexão
            }
        })
    }



}


