package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityRecSenhaBinding
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException

class ActivityRecSenha: AppCompatActivity() {

    private lateinit var binding: ActivityRecSenhaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecSenhaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btRecSenha.setOnClickListener{
            val email = binding.editEmail.text.toString()
            val novaSenha = binding.editRecSenha.text.toString()
            atualizarSenha(email, novaSenha)
        }



    }





    fun atualizarSenha(email: String, novaSenha: String) {
        var conn: Connection? = null
        var stmt: PreparedStatement? = null
        try {
            // Configuração da conexão com o banco de dados
            val url = "jdbc:mysql://<seu-host>:<minha-porta>/<meu-banco-de-dados>"
            val user = "<meu-usuário>"
            val password = "<minha-senha>"
            conn = DriverManager.getConnection(url, user, password)

            // Query de atualização da senha
            val query = "UPDATE usuarios SET senha = ? WHERE email = ?"
            stmt = conn.prepareStatement(query)
            stmt.setString(1, novaSenha)
            stmt.setString(2, email)

            // Executa a query
            val rowsUpdated = stmt.executeUpdate()
            if (rowsUpdated > 0) {
                println("Senha atualizada com sucesso!")
            } else {
                println("Email não encontrado.")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            // Fecha a conexão e o statement
            stmt?.close()
            conn?.close()
        }
    }



}