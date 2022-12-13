package com.example.secureauthentication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.secureauthentication.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Splash : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.isUserRegistered.observe(this, onUserRegistered)
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.checkUserRegistered()
        }, 3000)
    }

    private val onUserRegistered = Observer<Boolean> { isRegistered ->
        if (isRegistered) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            finish()
        }

    }
}