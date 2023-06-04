package com.example.myapplication

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
                    // Limpar as informações de autenticação localmente
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




/* val email = "<email-do-usuario-logado>"
        val jogador = buscarJogador(email)

        if (jogador != null) {

            jogador.nome = binding.textNomeUsuario.toString()
            jogador.posicao = binding.textPosicao.toString()
            jogador.altura = binding.textAltura.toString().toDouble()
            jogador.nacionalidade = binding.textNacionalidade.toString()
            jogador.peDominante = binding.textPeDominante.toString()*/


    /*fun buscarJogador(email: String): Jogador? {
        var conn: Connection? = null
        var stmt: PreparedStatement? = null
        var rs: ResultSet? = null
        try {
            // Configuração da conexão com o banco de dados
            val url = "jdbc:mysql://<seu-host>:<sua-porta>/<seu-banco-de-dados>"
            val user = "<seu-usuário>"
            val password = "<sua-senha>"
            conn = DriverManager.getConnection(url, user, password)

            // Query de seleção das informações do usuário
            val query =
                "SELECT nome, posicao, altura, nacionalidade, pe_dominante FROM usuarios WHERE email = ?"
            stmt = conn.prepareStatement(query)
            stmt.setString(1, email)

            // Executa a query
            rs = stmt.executeQuery()

            // Cria um objeto Jogador com as informações do usuário encontrado
            if (rs.next()) {
                val nome = rs.getString("nome")
                val posicao = rs.getString("posicao")
                val altura = rs.getDouble("altura")
                val nacionalidade = rs.getString("nacionalidade")
                val peDominante = rs.getString("pe_dominante")

                return Jogador(nome, posicao,
                    altura.toString().toDouble().toInt(),
                    nacionalidade,
                    peDominante)
            } else {
                return null
            }
        } catch (e: SQLException) {
            e.printStackTrace()
            return null
        } finally {
            // Fecha a conexão, o statement e o resultset
            rs?.close()
            stmt?.close()
            conn?.close()
        }
    }*/
