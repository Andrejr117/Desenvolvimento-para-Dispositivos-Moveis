package com.example.myapplication.ClassLayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityDirecionamentoBinding

class ActivityDirecionamento : AppCompatActivity() {

    private lateinit var binding: ActivityDirecionamentoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDirecionamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        val buttonEntrarSala = binding.btEntrarSala
        buttonEntrarSala.setOnClickListener{
//
//            exibirInputDialog()

        }

        val buttonCriarSala = binding.btCriarSala
        buttonCriarSala.setOnClickListener{
            val intent = Intent(this, ActivitySalaSorteio::class.java)
            startActivity(intent)

        }

        val buttonPerfil = binding.btPerfil
        buttonPerfil.setOnClickListener {
            val intent = Intent(this, ActivityPerfil::class.java)
            startActivity(intent)
        }



    }




//    private fun criarSala() {
//        // Gerar um código único para a sala
//        val codigoSala = gerarCodigoSala()
//
//        // Chamar a API para criar a sala usando o código gerado
//        val retrofit = ApiManager.getRetrofitInstance()
//        val apiService = retrofit.create(MyApi::class.java)
//        val call = apiService.criarSala(codigoSala)
//
//        call.enqueue(object : Callback<ApiResponse> {
//            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
//                if (response.isSuccessful) {
//                    // Sala criada com sucesso, redirecionar para a tela da sala
//                    val intent = Intent(this@ActivityDirecionamento, ActivitySalaSorteio::class.java)
//                    intent.putExtra("codigoSala", codigoSala)
//                    startActivity(intent)
//                } else {
//                    // Tratar resposta de erro
//                    Toast.makeText(this@ActivityDirecionamento, "Erro ao criar sala", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
//                // Tratar erro de conexão
//                Toast.makeText(this@ActivityDirecionamento, "Erro de conexão", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }




//
//
//    fun exibirInputDialog() {
//         val retrofit = ApiManager.getRetrofitInstance()
//         val apiService = retrofit.create(MyApi::class.java)
//            val context = ApplicationProvider.getApplicationContext<Application>()
//            val inputEditText = EditText(context)
//            inputEditText.inputType = InputType.TYPE_CLASS_TEXT
//
//            val dialog = AlertDialog.Builder(context)
//                .setTitle("Digite o código")
//                .setView(inputEditText)
//                .setPositiveButton("Enviar") { _, _ ->
//                    val codigo = inputEditText.text.toString()
//                    enviarCodigo(codigo)
//                }
//                .setNegativeButton("Cancelar", null)
//                .create()
//
//            dialog.show()
//        }

//        private fun enviarCodigo(codigo: String) {
//
//            val retrofit = ApiManager.getRetrofitInstance()
//            val apiService = retrofit.create(MyApi::class.java)
//
//            // Faça a chamada à API para verificar o código
//            val call = apiService.verificarCodigo(codigo)
//            call.enqueue(object : Callback<VerificarCodigoResponse> {
//                override fun onResponse(
//                    call: Call<VerificarCodigoResponse>,
//                    response: Response<VerificarCodigoResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        // Código válido, redirecionar para a outra tela do aplicativo
//                        // e adicionar o jogador à lista de jogadores
//
//                        // Ação de redirecionamento para outra tela do aplicativo
//                        val intent = Intent(this@ActivityDirecionamento, ActivitySalaSorteio::class.java)
//                        startActivity(intent)
//
//                        // Adicionar o jogador à lista de jogadores
//                        // Exemplo:
//                        val jogador = response.body()?.jogador
//                        jogador?.let { Sala.add(it) }
//                    } else {
//                        // Código inválido, tratar erro
//                    }
//                }



//                // Restante do código do Callback
//            })
//        }


}

// sem esse ele tbm dá erro kkk
