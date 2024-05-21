package org.prasadmhapankar.kmpmuseum.data

import kotlinx.coroutines.flow.Flow

interface MuseumRepository {
    suspend fun getData(): Flow<List<MuseumObject>>

    suspend fun saveObjects(newObjects: List<MuseumObject>)

    fun getObjects(): Flow<List<MuseumObject>>

    fun getObjectById(objectId: Int): Flow<MuseumObject?>
}