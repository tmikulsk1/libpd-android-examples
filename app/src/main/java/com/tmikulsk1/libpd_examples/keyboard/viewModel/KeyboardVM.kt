package com.tmikulsk1.libpd_examples.keyboard.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.tmikulsk1.libpd_examples.keyboard.model.EMPTY_PD_PATCH
import com.tmikulsk1.libpd_examples.keyboard.model.PDAudioConfig
import com.tmikulsk1.libpd_examples.keyboard.model.PD_PATCH_NAME
import org.puredata.android.io.PdAudio
import org.puredata.android.utils.PdUiDispatcher
import org.puredata.core.PdBase
import org.puredata.core.utils.IoUtils
import java.io.File
import java.io.IOException
import java.io.InputStream


class KeyboardVM(
    pdInputStream: InputStream,
    filesDir: File,
    audioConfig: PDAudioConfig,
    private val pdDispatcher: PdUiDispatcher
) : ViewModel() {

    private var pdPatch: Int

    init {
        pdPatch = getPdFilePatch(filesDir, pdInputStream)
        configureReceiver(pdDispatcher)
        configureAudio(audioConfig)
    }

    private fun getPdFilePatch(
        filesDir: File,
        pdInputStream: InputStream
    ): Int {
        return try {
            IoUtils.extractZipResource(pdInputStream, filesDir, true)
            val pdFile = File(filesDir, PD_PATCH_NAME)
            PdBase.openPatch(pdFile)
        } catch (exception: IOException) {
            Log.e(exception.message, exception.localizedMessage.orEmpty())
            EMPTY_PD_PATCH
        }
    }

    private fun configureReceiver(pdDispatcher: PdUiDispatcher) {
        PdBase.setReceiver(pdDispatcher)
    }

    private fun configureAudio(audioConfig: PDAudioConfig) {
        PdAudio.initAudio(
            audioConfig.sampleRate,
            audioConfig.inChannels,
            audioConfig.outChannels,
            audioConfig.ticksPerBuffer,
            audioConfig.restart
        )
    }

    fun send(receiver: String) {
        PdBase.sendBang(receiver)
    }

    fun onDestroy() {
        if (pdPatch != EMPTY_PD_PATCH) {
            PdBase.closePatch(pdPatch)
            pdPatch = EMPTY_PD_PATCH
        }

        pdDispatcher.release()
        PdBase.release()
    }

    fun onStop() {
        PdAudio.stopAudio()
    }

    fun onResume(context: Context) {
        PdAudio.startAudio(context)
    }
}