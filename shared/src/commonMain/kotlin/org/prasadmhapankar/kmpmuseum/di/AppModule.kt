package org.prasadmhapankar.kmpmuseum.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.prasadmhapankar.kmpmuseum.data.InMemoryMuseumStorage
import org.prasadmhapankar.kmpmuseum.data.KtorMuseumApi
import org.prasadmhapankar.kmpmuseum.data.MuseumApi
import org.prasadmhapankar.kmpmuseum.data.MuseumRepository
import org.prasadmhapankar.kmpmuseum.data.MuseumRepositoryImpl
import org.prasadmhapankar.kmpmuseum.data.MuseumStorage

val appModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Any)
            }
        }
    }
    single<MuseumStorage> { InMemoryMuseumStorage() }
    single<MuseumApi> { KtorMuseumApi(get()) }
    single<MuseumRepository> { MuseumRepositoryImpl(get(), get())}
}