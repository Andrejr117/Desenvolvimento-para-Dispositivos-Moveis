package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityRecSenhaBinding

class ActivityRecSenha: AppCompatActivity() {

    private lateinit var binding: ActivityRecSenhaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecSenhaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

    }


}