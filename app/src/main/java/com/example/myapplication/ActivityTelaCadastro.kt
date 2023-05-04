package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivityTelaCadastroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.ktx.Firebase

class ActivityTelaCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityTelaCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

    }

    fun cadastrarUsuario(jogador: Jogador) {
        // Realizar validações necessárias (por exemplo, checar se o email é válido)
        // ...

        // Criar uma nova conta de usuário com email e senha
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(jogador.email, jogador.senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Se o cadastro for bem-sucedido, atualizar o perfil do usuário com os dados fornecidos
                    val usuarioFirebase = FirebaseAuth.getInstance().currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(jogador.nome)
                        .build()

                    usuarioFirebase?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Se o perfil for atualizado com sucesso, salvar os outros dados do usuário no banco de dados
                                val db = Firebase.firestore
                                db.collection("usuarios").document(usuarioFirebase.uid)
                                    .set(jogador)
                                    .addOnSuccessListener {
                                        // Se os dados do usuário forem salvos com sucesso, exibir uma mensagem de sucesso para o usuário
                                        Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                                    }
                                    .addOnFailureListener {
                                        // Se houver um erro ao salvar os dados do usuário, exibir uma mensagem de erro para o usuário
                                        Toast.makeText(this, "Erro ao salvar dados do usuário.", Toast.LENGTH_SHORT).show()
                                    }
                            } else {
                                // Se houver um erro ao atualizar o perfil, exibir uma mensagem de erro para o usuário
                                Toast.makeText(this, "Erro ao atualizar perfil do usuário.", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    // Se o cadastro falhar, exibir uma mensagem de erro para o usuário
                    Toast.makeText(this, "Falha ao cadastrar usuário. Verifique seu email e senha e tente novamente.", Toast.LENGTH_SHORT).show()
                }
            }
    }



}