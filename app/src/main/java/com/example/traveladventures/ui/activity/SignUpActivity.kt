package com.example.traveladventures.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.traveladventures.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}