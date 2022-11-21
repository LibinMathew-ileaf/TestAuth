package com.example.secureauthentication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.secureauthentication.databinding.ActivityMainBinding
import com.example.secureauthentication.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var   binding:ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.buttonLogout.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
            finish()
        }
        viewModel.onFetchDetails.observe(this, onFetchUserDetails)
        viewModel.getUserDetail()
    }

    @SuppressLint("SetTextI18n")
    private val onFetchUserDetails = Observer<String> { userDetails ->
        if(userDetails.isNotEmpty()){
        val user=    userDetails.split('/')
            binding.textUserDetail.text= "Hello Welcome \n"+user[0]
        }

    }
}