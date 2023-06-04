package com.example.myapplication.Class

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityItemUsuarioBinding

class JogadorAdapter(private val jogador: List<String>) : RecyclerView.Adapter<JogadorAdapter.JogadorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JogadorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityItemUsuarioBinding.inflate(inflater, parent, false)
        return JogadorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JogadorViewHolder, position: Int) {
        val jogador = jogador[position]
        holder.bind(jogador)
    }

    override fun getItemCount(): Int {
        return jogador.size
    }

    inner class JogadorViewHolder(private val binding: ActivityItemUsuarioBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(jogador: String) {
            binding.textNomeUsuario.text = jogador
        }
    }
}