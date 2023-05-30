package com.example.myapplication.Class

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.example.myapplication.ActivityRecSenha

data class LoginResponse(

    val token: String,
    val userId: String,
    val userName: String

) {

}