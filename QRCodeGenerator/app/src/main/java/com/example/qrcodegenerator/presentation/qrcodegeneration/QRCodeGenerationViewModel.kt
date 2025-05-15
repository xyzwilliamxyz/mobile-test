package com.example.qrcodegenerator.presentation.qrcodegeneration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrcodegenerator.core.dispatcher.CoroutineDispatcherProvider
import com.example.qrcodegenerator.domain.interactor.GenerateSeedInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TIMER_DELAY = 1000L

@HiltViewModel
class QRCodeGenerationViewModel @Inject constructor(
    private val generateSeedInteractor: GenerateSeedInteractor,
    private val dispatcher: CoroutineDispatcherProvider
) : ViewModel() {
    private val _state = MutableStateFlow(QRCodeGenerationState())
    val state = _state.asStateFlow()

    private var timerJob: Job? = null

    init {
        generateSeed()
    }

    private fun generateSeed() {
        viewModelScope.launch(dispatcher.io()) {
            _state.value = QRCodeGenerationState(
                isLoading = true,
                seed = "",
                timeRemaining = 0
            )
            runCatching {
                val result = generateSeedInteractor()
                _state.value = QRCodeGenerationState(
                    isLoading = false,
                    seed = result.seed,
                    timeRemaining = result.secondsRemaining,
                )
                startTimer()
            }.onFailure {
                _state.value = QRCodeGenerationState(
                    isLoading = false,
                    seed = "",
                    timeRemaining = 0
                )
            }
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch(dispatcher.io()) {
            while (true) {
                delay(TIMER_DELAY)
                val timeRemaining = _state.value.timeRemaining
                if (timeRemaining > 0) {
                    _state.value = _state.value.copy(timeRemaining = timeRemaining - 1)
                } else {
                    break
                }
            }
        }
    }
}

data class QRCodeGenerationState(
    val seed: String = "",
    val isLoading: Boolean = false,
    val timeRemaining: Int = 0
) {
    fun formatTimeRemaining(): String {
        return "${timeRemaining}s..."
    }

    fun isSeedValid(): Boolean {
        return seed.isNotBlank()
    }
}
