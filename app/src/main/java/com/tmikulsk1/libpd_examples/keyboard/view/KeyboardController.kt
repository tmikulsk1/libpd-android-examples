package com.tmikulsk1.libpd_examples.keyboard.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tmikulsk1.libpd_examples.R
import com.tmikulsk1.libpd_examples.databinding.ActivityKeyboardControllerBinding

class KeyboardController : AppCompatActivity() {

    private lateinit var binding: ActivityKeyboardControllerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKeyboardControllerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}