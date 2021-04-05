package com.tmikulsk1.libpd_examples.keyboard.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tmikulsk1.libpd_examples.keyboard.model.PDAudioConfig
import org.puredata.android.utils.PdUiDispatcher
import java.io.File
import java.io.InputStream

@Suppress("UNCHECKED_CAST")
class KeyboardVMFactory(
    private val pdInputStream: InputStream,
    private val filesDir: File,
    private val audioConfig: PDAudioConfig,
    private val pdDispatcher: PdUiDispatcher
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return KeyboardVM(
            pdInputStream,
            filesDir,
            audioConfig,
            pdDispatcher
        ) as T
    }
}