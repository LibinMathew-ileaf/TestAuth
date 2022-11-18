package com.example.secureauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.secureauthentication.databinding.ActivityMainBinding
import com.example.secureauthentication.databinding.ActivitySignInBinding
import com.example.secureauthentication.databinding.ActivitySignUpBinding
import com.example.secureauthentication.viewmodel.SignInViewModel
import com.example.secureauthentication.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignIn : AppCompatActivity() {
    private val viewModel: SignInViewModel by viewModels()

    private  lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.buttonSignUp.setOnClickListener {
          val userName=  binding.editUserName.text.toString()
          val password=  binding.editPassword.text.toString()
            viewModel.signInUser(userName,password);

        }
        binding.textRegister.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            finish()
        }

    }
}