package com.example.secureauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.secureauthentication.databinding.ActivitySignInBinding
import com.example.secureauthentication.utils.ToastHelper
import com.example.secureauthentication.viewmodel.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignIn : AppCompatActivity() {
    private val viewModel: SignInViewModel by viewModels()
    @Inject
    lateinit var toastHelper: ToastHelper

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel.onSignInUserSuccess.observe(this, onSuccessSignIn)
        viewModel.onSignInUserError.observe(this, onErrorSignIn)

        binding.buttonSignUp.setOnClickListener {
            val userName = binding.editUserName.text.toString()
            val password = binding.editPassword.text.toString()
            viewModel.signInUser(userName, password)

        }
        binding.textRegister.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            finish()
        }

    }
    private val onSuccessSignIn = Observer<Boolean> { isSuccess ->
        if(isSuccess){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            toastHelper.showToast(getString(R.string.warning_error))
        }

    }

    private val onErrorSignIn = Observer<String> { error ->
        toastHelper.showToast(error)


    }
}