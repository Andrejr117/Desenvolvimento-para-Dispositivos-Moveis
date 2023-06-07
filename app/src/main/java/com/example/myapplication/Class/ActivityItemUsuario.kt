package com.example.myapplication.Class

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityItemUsuarioBinding

class ActivityItemUsuario : AppCompatActivity() {

    lateinit var binding: ActivityItemUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}