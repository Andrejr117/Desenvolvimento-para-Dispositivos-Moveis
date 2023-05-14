package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
            entrarSala()
        }


        val buttonPerfil = binding.btPerfil
        buttonPerfil.setOnClickListener {
            val intent = Intent(this, ActivityPerfil::class.java)
            startActivity(intent)
        }



    }

    fun entrarSala() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Entrar em uma Sala")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_NUMBER
        input.maxLines = 1
        input.hint = "Digite o código da Sala"
        builder.setView(input)

        builder.setPositiveButton("Entrar") { _, _ ->
            val lobbyCode = input.text.toString()

            val dbHelper = MyDatabaseHelper(this)
            val db = dbHelper.readableDatabase
            val projection = arrayOf(BaseColumns._ID)
            val selection = "${DatabaseContract.LobbyEntry.COLUMN_CODE} = ?"
            val selectionArgs = arrayOf(lobbyCode)
            val cursor = db.query(
                DatabaseContract.LobbyEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
            )

            if (cursor.count > 0) {
                // A sala existe, você pode adicionar o usuário à sala aqui
            } else {
                Toast.makeText(this, "Sala não encontrada", Toast.LENGTH_SHORT).show()
            }
            cursor.close()
            db.close()
        }

        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.cancel() }

        builder.show()
    }





}