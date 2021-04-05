package com.tmikulsk1.libpd_examples.keyboard.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tmikulsk1.libpd_examples.R
import com.tmikulsk1.libpd_examples.databinding.ActivityKeyboardControllerBinding
import com.tmikulsk1.libpd_examples.keyboard.model.PDAudioConfig
import com.tmikulsk1.libpd_examples.keyboard.model.PDBang
import com.tmikulsk1.libpd_examples.keyboard.viewModel.KeyboardVM
import com.tmikulsk1.libpd_examples.keyboard.viewModel.KeyboardVMFactory
import org.puredata.android.utils.PdUiDispatcher

class KeyboardController : AppCompatActivity() {

    private lateinit var binding: ActivityKeyboardControllerBinding

    private val viewModel: KeyboardVM by viewModels {
        KeyboardVMFactory(
            resources.openRawResource(R.raw.pdfolder),
            filesDir,
            PDAudioConfig.getDefaultConfigs(),
            PdUiDispatcher()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKeyboardControllerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNaturalButtonsActions()
        setupSharpButtonsActions()
    }

    private fun setupNaturalButtonsActions() {
        binding.btnC.setOnClickListener { viewModel.send(PDBang.Natural.C_NOTE) }
        binding.btnD.setOnClickListener { viewModel.send(PDBang.Natural.D_NOTE) }
        binding.btnE.setOnClickListener { viewModel.send(PDBang.Natural.E_NOTE) }
        binding.btnF.setOnClickListener { viewModel.send(PDBang.Natural.F_NOTE) }
        binding.btnG.setOnClickListener { viewModel.send(PDBang.Natural.G_NOTE) }
        binding.btnA.setOnClickListener { viewModel.send(PDBang.Natural.A_NOTE) }
        binding.btnB.setOnClickListener { viewModel.send(PDBang.Natural.B_NOTE) }
    }

    private fun setupSharpButtonsActions() {
        binding.btnCSharp.setOnClickListener { viewModel.send(PDBang.Sharp.C_NOTE) }
        binding.btnDSharp.setOnClickListener { viewModel.send(PDBang.Sharp.D_NOTE) }
        binding.btnFSharp.setOnClickListener { viewModel.send(PDBang.Sharp.F_NOTE) }
        binding.btnGSharp.setOnClickListener { viewModel.send(PDBang.Sharp.G_NOTE) }
        binding.btnASharp.setOnClickListener { viewModel.send(PDBang.Sharp.A_NOTE) }

    }

    override fun onResume() {
        viewModel.onResume(this)
        super.onResume()
    }

    override fun onStop() {
        viewModel.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }
}