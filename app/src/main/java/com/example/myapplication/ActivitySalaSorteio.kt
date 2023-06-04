package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivitySalaSorteioBinding
import com.example.myapplication.networkconection.MyApi
import com.example.myapplication.Class.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.content.Intent

class ActivitySalaSorteio : AppCompatActivity() {

    private lateinit var binding: ActivitySalaSorteioBinding
    private val listaJogadores = mutableListOf<Jogador>()
    private lateinit var apiService: MyApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalaSorteioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val roomCode = intent.getStringExtra("roomCode")
        binding.textNSala.text = roomCode

        val retrofit = Retrofit.Builder()
            .baseUrl("/jogador") // Substitua pela URL base da sua API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(MyApi::class.java)

        binding.btSortear.setOnClickListener {
            adicionarJogador()
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val jogadores = obterJogadores()
                    val jogadoresSorteados = listaJogadores.shuffled() // Embaralhar a lista de jogadores
                    val time1 = jogadoresSorteados.subList(0, jogadoresSorteados.size / 2) // Primeira metade dos jogadores
                    val time2 = jogadoresSorteados.subList(jogadoresSorteados.size / 2, jogadoresSorteados.size) // Segunda metade dos jogadores

                    // Redirecionar para a tela de times definidos
                    val intent = Intent(this@ActivitySalaSorteio, ActivityTimesDefinidos::class.java)
                    intent.putExtra("time1", time1.toTypedArray()) // Passar o time 1 como um array para a próxima tela
                    intent.putExtra("time2", time2.toTypedArray()) // Passar o time 2 como um array para a próxima tela
                    startActivity(intent)

                } catch (e: Exception) {
                    // Trate o erro adequadamente
                }
            }
        }
    }

    private suspend fun obterJogadores(): List<String> {
        val response = apiService.obterJogadores()
        if (response.isSuccessful) {
            return response.body()?.jogadores ?: emptyList()
        } else {
            throw Exception("Falha ao obter jogadores")
        }
    }


    private fun adicionarJogador() {
        val nome = "Nome do jogador" // Substitua pela lógica para obter o nome do jogador
        val jogador = Jogador(nome)
        listaJogadores.add(jogador)
    }

}
