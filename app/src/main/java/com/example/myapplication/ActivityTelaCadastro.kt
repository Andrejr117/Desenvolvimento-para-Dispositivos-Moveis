package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.Class.ApiManager
import com.example.myapplication.Class.Jogador
import com.example.myapplication.databinding.ActivityTelaCadastroBinding
import com.example.myapplication.networkconection.MyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.DriverManager

class ActivityTelaCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityTelaCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btCadastro.setOnClickListener{
            val nome: String = binding.editNome.text.toString()
            val email: String = binding.editEmail.text.toString()
            val senha: Int = binding.editSenha.text.toString().toInt()
            val endereco: String = binding.editEndereco.text.toString()
            val nacionalidade: String = binding.editNacionalidade.text.toString()
            val posicao: String = binding.editPosicao.text.toString()
            val peDominante: String = binding.editPeDominante.text.toString()
            val altura: Double = binding.editAltura.text.toString().toDouble()

            cadastrarUsuario(nome, email, senha, endereco, nacionalidade, posicao, peDominante, altura)
        }


}

    private fun cadastrarUsuario(
        nome: String,
        email: String,
        senha: Int,
        endereco: String,
        nacionalidade: String,
        posicao: String,
        peDominante: String,
        altura: Double
    ) {

        val retrofit = ApiManager.getRetrofitInstance()
        val apiService = retrofit.create(MyApi::class.java)

        val jogador = Jogador(
            nome = nome,
            email = email,
            senha = senha,
            endereco = endereco,
            nacionalidade = nacionalidade,
            posicao = posicao,
            peDominante = peDominante,
            altura = altura
        )

        val call = apiService.cadastrarJogador(jogador)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Cadastro realizado com sucesso
                    val intent = Intent(this@ActivityTelaCadastro, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // Trate a resposta de erro
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Trate o erro
            }
        })
    }



}








//bindinginding.btCadastro.setOnClickListener{
//            val jogador = Jogador(
//                nome = binding.editNome.text.toString(),
//                email = binding.editEmail.text.toString(),
//                senha = binding.editSenha.text.toString().toInt(),
//                endereco = binding.editEndereco.text.toString(),
//                nacionalidade = binding.editNacionalidade.text.toString(),
//                posicao = binding.editPosicao.text.toString(),
//                peDominante = binding.editPeDominante.text.toString(),
//                altura = binding.editAltura.text.toString().toDouble()
//            )
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//
//
//            val conn = DriverManager.getConnection("jdbc:mysql://<host>/<database>", "<user>", "<password>")
//            val statement = conn.createStatement()
//
//            val sql = "INSERT INTO usuarios (nome, email, senha, endereco, nacionalidade, posicao, pe_dominante, altura) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
//            val preparedStatement = conn.prepareStatement(sql)
//
//            preparedStatement.setString(1, jogador.nome)
//            preparedStatement.setString(2, jogador.email)
//            preparedStatement.setInt(3, jogador.senha)
//            preparedStatement.setString(4, jogador.endereco)
//            preparedStatement.setString(5, jogador.nacionalidade)
//            preparedStatement.setString(6, jogador.posicao)
//            preparedStatement.setString(7, jogador.peDominante)
//            preparedStatement.setDouble(8, jogador.altura)
//
//            preparedStatement.executeUpdate()
//
//            // fechar a conexão
//            preparedStatement.close()
//            conn.close()
//
//            // exibir mensagem de sucesso
//            Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()
//        }
//
//    }

