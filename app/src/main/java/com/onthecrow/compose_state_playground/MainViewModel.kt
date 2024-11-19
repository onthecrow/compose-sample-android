package com.onthecrow.compose_state_playground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

data class CompoundUIState(
    val textN1: Char = 'a',
    val textN2: Char = 'a',
    val textN3: Char = 'a',
    val textN4: Char = 'a',
    val textN5: Char = 'a',
    val textN6: Char = 'a',
    val textN7: Char = 'a',
    val textN8: Char = 'a',
    val textN9: Char = 'a',
    val textN10: Char = 'a',
    val textN11: Char = 'a',
    val textN12: Char = 'a',
)

class MainViewModel : ViewModel() {

    private val _compoundUIState = MutableStateFlow(CompoundUIState())
    private val _uiStatePieceN1 = MutableStateFlow('a')
    private val _uiStatePieceN2 = MutableStateFlow('a')
    private val _uiStatePieceN3 = MutableStateFlow('a')
    private val _uiStatePieceN4 = MutableStateFlow('a')
    private val _uiStatePieceN5 = MutableStateFlow('a')
    private val _uiStatePieceN6 = MutableStateFlow('a')
    private val _uiStatePieceN7 = MutableStateFlow('a')
    private val _uiStatePieceN8 = MutableStateFlow('a')
    private val _uiStatePieceN9 = MutableStateFlow('a')
    private val _uiStatePieceN10 = MutableStateFlow('a')
    private val _uiStatePieceN11 = MutableStateFlow('a')
    private val _uiStatePieceN12 = MutableStateFlow('a')

    val compoundUIState: StateFlow<CompoundUIState> = _compoundUIState.asStateFlow()
    val uiStatePieceN1: StateFlow<Char> = _uiStatePieceN1.asStateFlow()
    val uiStatePieceN2: StateFlow<Char> = _uiStatePieceN2.asStateFlow()
    val uiStatePieceN3: StateFlow<Char> = _uiStatePieceN3.asStateFlow()
    val uiStatePieceN4: StateFlow<Char> = _uiStatePieceN4.asStateFlow()
    val uiStatePieceN5: StateFlow<Char> = _uiStatePieceN5.asStateFlow()
    val uiStatePieceN6: StateFlow<Char> = _uiStatePieceN6.asStateFlow()
    val uiStatePieceN7: StateFlow<Char> = _uiStatePieceN7.asStateFlow()
    val uiStatePieceN8: StateFlow<Char> = _uiStatePieceN8.asStateFlow()
    val uiStatePieceN9: StateFlow<Char> = _uiStatePieceN9.asStateFlow()
    val uiStatePieceN10: StateFlow<Char> = _uiStatePieceN10.asStateFlow()
    val uiStatePieceN11: StateFlow<Char> = _uiStatePieceN11.asStateFlow()
    val uiStatePieceN12: StateFlow<Char> = _uiStatePieceN12.asStateFlow()

    init {
        viewModelScope.launch {
            launchFieldModifications()
        }
    }

    /**
     * Launches a coroutine that continuously modifies the UI state fields.
     *
     * This function runs in an infinite loop, triggering character emissions for
     * each UI state field. It uses `launchCharEmissionForActions` to handle
     * the emission and updating of individual UI state pieces with each emitted value
     * on worker thread.
     *
     * After each iteration of character emissions, it introduces a delay of 1 second to allow the
     * user to observe the consistency of the UI state before starting the next iteration.
     *
     * **Note:** This function is designed to run indefinitely and should be launched
     * within a coroutine scope that manages its lifecycle.
     */
    private suspend fun launchFieldModifications() {
        while (true) {
            launchCharEmissionForActions(
                { char -> _uiStatePieceN1.update { char } },
                { char -> _uiStatePieceN2.update { char } },
                { char -> _uiStatePieceN3.update { char } },
                { char -> _uiStatePieceN4.update { char } },
                { char -> _uiStatePieceN5.update { char } },
                { char -> _uiStatePieceN6.update { char } },
                { char -> _uiStatePieceN7.update { char } },
                { char -> _uiStatePieceN8.update { char } },
                { char -> _uiStatePieceN9.update { char } },
                { char -> _uiStatePieceN10.update { char } },
                { char -> _uiStatePieceN11.update { char } },
                { char -> _uiStatePieceN12.update { char } },
                { char -> _compoundUIState.update { _compoundUIState.value.copy(textN1 = char) } },
                { char -> _compoundUIState.update { _compoundUIState.value.copy(textN2 = char) } },
                { char -> _compoundUIState.update { _compoundUIState.value.copy(textN3 = char) } },
                { char -> _compoundUIState.update { _compoundUIState.value.copy(textN4 = char) } },
                { char -> _compoundUIState.update { _compoundUIState.value.copy(textN5 = char) } },
                { char -> _compoundUIState.update { _compoundUIState.value.copy(textN6 = char) } },
                { char -> _compoundUIState.update { _compoundUIState.value.copy(textN7 = char) } },
                { char -> _compoundUIState.update { _compoundUIState.value.copy(textN8 = char) } },
                { char -> _compoundUIState.update { _compoundUIState.value.copy(textN9 = char) } },
                { char -> _compoundUIState.update { _compoundUIState.value.copy(textN10 = char) } },
                { char -> _compoundUIState.update { _compoundUIState.value.copy(textN11 = char) } },
                { char -> _compoundUIState.update { _compoundUIState.value.copy(textN12 = char) } },
            ).joinAll()
            delay(1000)
        }
    }

    /**
     * This function simulates fast (every 1ms) emission of data in worker threads.
     *
     * This function creates and starts a separate coroutine for each provided action.
     * Each coroutine repeatedly emits characters from 'a' to 'z' with 1ms delay,
     * simulating a stream of data. This is intended for testing or demonstration purposes
     * to see how the system handles a rapid influx of data.
     *
     * The emission runs in a background thread (Dispatchers.Default) to avoid blocking the main thread.
     *
     * @param actions An array of functions that accept a single character as input.
     *                These functions will be called with each emitted character.
     *
     * @return A list of [Job] objects representing the launched coroutines. These can be used
     *         to control the execution of the coroutines, such as canceling them.
     */
    private fun launchCharEmissionForActions(vararg actions: (Char) -> Unit): List<Job> {
        return actions.map { action ->
            viewModelScope.launch(Dispatchers.Default) {
                repeat(6) {
                    for (char in 'a'..'z') {
                        action(char)
                        delay(1)
                    }
                }
            }
        }
    }
}