package org.prasadmhapankar.kmpmuseum.presentation

import org.prasadmhapankar.kmpmuseum.model.UiState

class ListScreenContract {
    sealed class State: UiState {
        data object Loading: State()
        data class Success(
            val museumListState: MuseumListState?
        ): State()
    }
}