package org.prasadmhapankar.kmpmuseum.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MuseumRepositoryImpl(
    private val museumApi: MuseumApi,
    private val museumStorage: MuseumStorage,
) : MuseumRepository {

    override suspend fun getData(): Flow<List<MuseumObject>> = flow {
        emit(museumApi.getData())
    }

    override suspend fun saveObjects(newObjects: List<MuseumObject>) =
        museumStorage.saveObjects(newObjects)

    override fun getObjects(): Flow<List<MuseumObject>> =
        museumStorage.getObjects()

    override fun getObjectById(objectId: Int): Flow<MuseumObject?> =
        museumStorage.getObjectById(objectId)

}
