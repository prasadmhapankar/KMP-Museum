package org.prasadmhapankar.kmpmuseum.di

import org.koin.dsl.module
import org.prasadmhapankar.kmpmuseum.presentation.DetailViewModel
import org.prasadmhapankar.kmpmuseum.presentation.ListViewModel

val viewModelModule = module {
    factory {
        ListViewModel(
            museumRepository = get()
        )
    }
    factory {
        DetailViewModel(
            museumRepository = get()
        )
    }
}