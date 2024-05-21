package org.prasadmhapankar.kmpmuseum.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.prasadmhapankar.kmpmuseum.model.UiState

abstract class BaseViewModel<State : UiState> : ViewModel() {

    // Create Initial State of View
    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    // Get Current State
    val currentState: State
        get() = uiState.value

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    /**
     * Set new Ui State
     */
    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

}