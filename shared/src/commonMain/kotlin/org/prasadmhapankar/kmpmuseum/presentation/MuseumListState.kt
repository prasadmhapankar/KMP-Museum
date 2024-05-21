package org.prasadmhapankar.kmpmuseum.presentation

import org.prasadmhapankar.kmpmuseum.data.MuseumObject

data class MuseumListState(
    val list: List<MuseumObject>? = emptyList()
)
