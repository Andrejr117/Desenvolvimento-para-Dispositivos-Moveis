package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.databinding.ActivityDirecionamentoBinding

class ActivityDirecionamento : AppCompatActivity() {

    private lateinit var binding: ActivityDirecionamentoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDirecionamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()







        val buttonPerfil = binding.btPerfil
        buttonPerfil.setOnClickListener {
            val intent = Intent(this, ActivityPerfil::class.java)
            startActivity(intent)
        }



    }
}