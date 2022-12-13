package com.example.secureauthentication

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.secureauthentication.databinding.ActivitySignUpBinding
import com.example.secureauthentication.utils.ToastHelper
import com.example.secureauthentication.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUp : AppCompatActivity() {
    private val viewModel: SignUpViewModel by viewModels()
    @Inject
    lateinit var toastHelper: ToastHelper
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.onSaveUserData.observe(this, onSaveUser)
        binding.buttonSignUp.setOnClickListener {
            val confirmPassword= binding.editConfirmPassword.text.toString()
            val password= binding.editPassword.text.toString()
            val userName= binding.editUserName.text.toString()
            val name= binding.editName.text.toString()
            saveUserData(name, userName, password, confirmPassword)
        }


    }

    private val onSaveUser = Observer<Boolean> { isRegistered ->
        if(isRegistered){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            toastHelper.showToast(getString(R.string.warning_error))
        }

    }
    private fun saveUserData(
        name: String,
        userName: String,
        password: String,
        confirmPassword: String
    ) {
        if (name.isEmpty()) {
            toastHelper.showToast("Name Not Empty")
            return
        }
        if (userName.isEmpty()) {
            toastHelper.showToast("User Name Not Empty")
            return
        }
        if (password.isEmpty()) {
            toastHelper.showToast("Password Not Empty")
            return
        }
        if (confirmPassword.isEmpty()) {
            toastHelper.showToast("Confirm Password Not Empty")
            return
        }

        if (password == confirmPassword) {
            viewModel.saveUserData(name, userName, password)
        } else {
            toastHelper.showToast(" Password Not Match")
        }
    }
}