package com.example.qrcodegenerator.testutils

import com.example.qrcodegenerator.domain.model.Seed

object MockFactory {
    fun getSeed(timeRemaining: Int = 10): Seed {
        return Seed("test-seed", "2025-05-15T02:39:49.099Z", timeRemaining)
    }
}
