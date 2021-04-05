package com.tmikulsk1.libpd_examples.keyboard.model

import org.puredata.android.io.AudioParameters

data class PDAudioConfig(
    val sampleRate: Int,
    val inChannels: Int,
    val outChannels: Int,
    val ticksPerBuffer: Int,
    val restart: Boolean
) {
    companion object {
        fun getDefaultConfigs() = PDAudioConfig(
            sampleRate = AudioParameters.suggestSampleRate(),
            inChannels = 0,
            outChannels = 2,
            ticksPerBuffer = 8,
            restart = true
        )
    }
}
