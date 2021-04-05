package com.tmikulsk1.libpd_examples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tmikulsk1.libpd_examples.databinding.ActivityMainBinding
import com.tmikulsk1.libpd_examples.keyboard.view.KeyboardController

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRoutes()
    }

    private fun setupRoutes() {
        binding.btnMusicKeyboard.setOnClickListener {
            routeToKeyboardController()
        }
    }

    private fun routeToKeyboardController() {
        val intent = Intent(this, KeyboardController::class.java)
        startActivity(intent)
    }
}