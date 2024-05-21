package org.prasadmhapankar.kmpmuseum.presentation

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.prasadmhapankar.kmpmuseum.data.MuseumRepository

class ListViewModel(private val museumRepository: MuseumRepository) : ViewModel() {

    private val _state = kotlinx.coroutines.flow.MutableStateFlow(MuseumListState())
    @NativeCoroutinesState
    val featureListState = _state.asStateFlow()

    /*@NativeCoroutinesState
    val featureListState: StateFlow<List<MuseumObject>> =
        museumRepository.getObjects()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
*/
    init {
        getMuseumData()
    }

    private fun getMuseumData() {
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
