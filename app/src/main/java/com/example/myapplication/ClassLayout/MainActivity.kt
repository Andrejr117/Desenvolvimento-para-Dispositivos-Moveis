package com.example.myapplication.ClassLayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.Class.ApiManager
import com.example.myapplication.Class.LoginResponse
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.networkconection.MyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        val buttonEntrar = binding.btEntrar
        buttonEntrar.setOnClickListener{
            val email: String = binding.editEmail.text.toString()
            val senha: Int = binding.editSenha.text.toString().toInt()

            realizarLogin(email, senha)
        }

//        binding.btEntrar.setOnClickListener {
//            val email: String = binding.editEmail.text.toString()
//            val senha: Int = binding.editSenha.text.toString().toInt()
//
//            realizarLogin(email, senha)
//        }

        }

    private fun realizarLogin(email: String, senha: Int) {

        val retrofit = ApiManager.getRetrofitInstance()
        val apiService = retrofit.create(MyApi::class.java)

        val call = apiService.realizarLogin(email, senha)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse: LoginResponse? = response.body()
                    if (loginResponse != null) {

                        // Login bem-sucedido
                        val intent = Intent(this@MainActivity, ActivityDirecionamento::class.java)
                        startActivity(intent)

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
                t.printStackTrace()
            }
        })
    }




}


