package org.prasadmhapankar.kmpmuseum.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.prasadmhapankar.kmpmuseum.data.MuseumRepository
import org.prasadmhapankar.kmpmuseum.presentation.DetailViewModel
import org.prasadmhapankar.kmpmuseum.presentation.ListViewModel

object SharedModuleDependencies : KoinComponent {

    val museumRepository: MuseumRepository by inject()

    val listViewModel: ListViewModel by inject()

    val detailViewModel: DetailViewModel by inject()
}