package org.prasadmhapankar.kmpmuseum.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.prasadmhapankar.kmpmuseum.data.MuseumRepository

class ListViewModel(private val museumRepository: MuseumRepository) : ViewModel() {

    private val _state = MutableStateFlow(MuseumListState())
    val featureListState = _state.asStateFlow()

    init {
        getMuseumData()
    }

    fun getMuseumData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = museumRepository.getData()
            println("Museum Api response - $response")
            _state.update {
                it.copy(list = response)
            }
            museumRepository.saveObjects(response)
        }
    }
}
