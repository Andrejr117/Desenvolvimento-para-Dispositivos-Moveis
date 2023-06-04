package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Class.AlterarSenhaResponse
import com.example.myapplication.Class.ApiManager
import com.example.myapplication.databinding.ActivityRecSenhaBinding
import com.example.myapplication.networkconection.MyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ActivityRecSenha: AppCompatActivity() {

    private lateinit var binding: ActivityRecSenhaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecSenhaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btRecSenha.setOnClickListener{
            val email: String = binding.editEmail.text.toString()
            val novaSenha: Int = binding.editRecSenha.text.toString().toInt()

            alterarSenha(email, novaSenha)
        }

    }


    private fun alterarSenha(email: String, novaSenha: Int) {

        val retrofit = ApiManager.getRetrofitInstance()
        val apiService = retrofit.create(MyApi::class.java)

        val call = apiService.alterarSenha(email, novaSenha)
        call.enqueue(object : Callback<AlterarSenhaResponse> {
            override fun onResponse(call: Call<AlterarSenhaResponse>, response: Response<AlterarSenhaResponse>) {
                if (response.isSuccessful) {
                    val alterarSenhaResponse: AlterarSenhaResponse? = response.body()
                    if (alterarSenhaResponse != null) {
                        // Senha modificada com sucesso
                        // Faça o que for necessário após a alteração da senha
                        val intent = Intent(this@ActivityRecSenha, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // Resposta inválida do servidor
                    }
                } else {
                    // Trate a resposta de erro
                }
            }

            override fun onFailure(call: Call<AlterarSenhaResponse>, t: Throwable) {
                t.printStackTrace()
            // Trate o erro
            }
        })
    }



}