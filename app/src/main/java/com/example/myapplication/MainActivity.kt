package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()

    }

    fun handleOnClickEvent(view: View){
        Toast.makeText(this,"OnClickEvent",
        Toast.LENGTH_SHORT).show()
    }

    private fun setUpView() {

        binding.btEntrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            if (email.equals("aluno@nassau.com.br") && senha.equals("alunonassau")) {
                Toast.makeText(this, "Logado com sucesso!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Email ou senha invalido", Toast.LENGTH_LONG).show()
            }

        }

    }

}