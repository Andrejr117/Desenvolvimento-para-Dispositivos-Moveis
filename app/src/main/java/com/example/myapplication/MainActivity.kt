package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.Class.Jogador
import com.example.myapplication.Class.LoginResponse
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.networkconection.MyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val textViewCadastrar = binding.textTelacadastro
        textViewCadastrar.setOnClickListener {
            val intent = Intent(this, ActivityTelaCadastro::class.java)
            startActivity(intent)
        }

        val textViewRecSenha = binding.textTelarecsenha
        textViewRecSenha.setOnClickListener {
            val intent = Intent(this, ActivityRecSenha::class.java)
            startActivity(intent)
        }


        binding.btEntrar.setOnClickListener {
            val email: String = binding.editEmail.text.toString()
            val senha: String = binding.editSenha.text.toString()

            realizarLogin(email, senha)
        }

        }

    private fun realizarLogin(email: String, senha: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://seu_servidor/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(MyApi::class.java)

        val call = apiService.realizarLogin(email, senha)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse: LoginResponse? = response.body()
                    if (loginResponse != null) {
                        // Login bem-sucedido
                        val token: String = loginResponse.token
                        // Faça o que for necessário com o token
                    } else {
                        // Resposta inválida do servidor
                    }
                } else {
                    // Trate a resposta de erro
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Trate o erro
            }
        })
    }




}



//lifecycleScope.launch {
//    try {
//        val posts = apiService.getPosts()
//        // Faça algo com os dados recebidos
//    } catch (e: Exception) {
//        // Lidar com erros da API
//    }
//}