package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.Class.ApiManager
import com.example.myapplication.Class.Jogador
import com.example.myapplication.Class.Sala
import com.example.myapplication.databinding.ActivitySalaSorteioBinding
import com.example.myapplication.networkconection.MyApi
import retrofit2.Call
import android.content.Intent

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ActivitySalaSorteio : AppCompatActivity() {

    lateinit var binding: ActivitySalaSorteioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalaSorteioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val roomCode = intent.getStringExtra("roomCode")
        binding.textNSala.text = roomCode


        SalaSort()

    }



    fun SalaSort(){

        val retrofit = ApiManager.getRetrofitInstance()
        val apiService = retrofit.create(MyApi::class.java)

        val jogador: List<Jogador>// Obtenha a lista de jogadores da sala/lobby

        val call = apiService.criarTimes()
        call.enqueue(object : retrofit2.Callback<Sala> {
            override fun onResponse(call: Call<Sala>, response: retrofit2.Response<Sala>) {
                if (response.isSuccessful) {
                    val resultado = response.body()
                    // Faça algo com o resultado
                } else {
                    // Trate a resposta de erro
                }
            }

            override fun onFailure(call: Call<Sala>, t: Throwable) {
                // Trate o erro
            }
        })
    }


}
