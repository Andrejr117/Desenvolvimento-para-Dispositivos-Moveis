package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityTelaCadastroBinding
import java.sql.DriverManager

class ActivityTelaCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityTelaCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        binding.btCadastro.setOnClickListener{
            val jogador = Jogador(
                nome = binding.editNome.text.toString(),
                email = binding.editEmail.text.toString(),
                senha = binding.editSenha.text.toString().toInt(),
                endereco = binding.editEndereco.text.toString(),
                nacionalidade = binding.editNacionalidade.text.toString(),
                posicao = binding.editPosicao.text.toString(),
                peDominante = binding.editPeDominante.text.toString(),
                altura = binding.editAltura.text.toString().toDouble()
            )

            val conn = DriverManager.getConnection("jdbc:mysql://<host>/<database>", "<user>", "<password>")
            val statement = conn.createStatement()

            val sql = "INSERT INTO usuarios (nome, email, senha, endereco, nacionalidade, posicao, pe_dominante, altura) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            val preparedStatement = conn.prepareStatement(sql)

            preparedStatement.setString(1, jogador.nome)
            preparedStatement.setString(2, jogador.email)
            preparedStatement.setInt(3, jogador.senha)
            preparedStatement.setString(4, jogador.endereco)
            preparedStatement.setString(5, jogador.nacionalidade)
            preparedStatement.setString(6, jogador.posicao)
            preparedStatement.setString(7, jogador.peDominante)
            preparedStatement.setDouble(8, jogador.altura)

            preparedStatement.executeUpdate()

            // fechar a conexão
            preparedStatement.close()
            conn.close()

            // exibir mensagem de sucesso
            Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()
        }

    }

}

