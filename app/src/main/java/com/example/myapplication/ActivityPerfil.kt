package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityPerfilBinding
import java.sql.*

class ActivityPerfil : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        val email = "<email-do-usuario-logado>"
        val jogador = buscarJogador(email)

        if (jogador != null) {
            
            jogador.nome = binding.textNomeUsuario.toString()
            jogador.posicao = binding.textPosicao.toString()
            jogador.altura = binding.textAltura.toString().toDouble()
            jogador.nacionalidade = binding.textNacionalidade.toString()
            jogador.peDominante = binding.textPeDominante.toString()


        }

    }


    fun buscarJogador(email: String): Jogador? {
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
                    altura.toString().toDouble().toInt(), nacionalidade, peDominante)
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
    }

}