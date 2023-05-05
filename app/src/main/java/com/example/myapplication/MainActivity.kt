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
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val editTextEmail = binding.editEmail
        val editTextSenha = binding.editSenha

        // Adiciona um listener de clique no botão de login
        binding.btEntrar.setOnClickListener {
            // Obtém os valores dos EditTexts
            val email = editTextEmail.text.toString()
            val senha = editTextSenha.text.toString().toInt()

            // Verifica se o email e a senha estão corretos usando MySQL
            val url = "http://seu-servidor.com/login.php?email=$email&senha=$senha"
            val request = StringRequest(
                Request.Method.GET, url,
                { response ->
                    if (response == "true") {
                        // Login bem-sucedido, redireciona o usuário para a próxima tela
                        val intent = Intent(this, ActivityDirecionamento::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Login falhou, exibe uma mensagem de erro para o usuário
                        Toast.makeText(this, "E-mail ou senha incorretos", Toast.LENGTH_SHORT).show()
                    }
                },
                { error ->
                    // Erro ao fazer a solicitação HTTP, exibe uma mensagem de erro para o usuário
                    Toast.makeText(this, "Erro ao fazer login: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            )
            // Adiciona a solicitação à fila de solicitações Volley
            Volley.newRequestQueue(this).add(request)
        }


        val textViewCadastrar = binding.textTelacadastro
        textViewCadastrar.setOnClickListener {
            val intent = Intent(this, ActivityTelaCadastro::class.java)
            startActivity(intent)
        }

        val textViewRecSenha = binding.textTelarecsenha
        textViewRecSenha.setOnClickListener{
            val intent = Intent(this, ActivityTelaCadastro::class.java)
            startActivity(intent)
        }




    }
}