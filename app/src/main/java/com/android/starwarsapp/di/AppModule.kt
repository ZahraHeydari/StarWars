package com.android.starwarsapp.di

import com.android.domain.repository.SwapiRepository
import com.android.domain.usecase.FetchCharactersUseCase
import com.android.domain.usecase.GetCharacterDetailUseCase
import com.android.presentation.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { createFetchCharactersUseCase(get()) }
    single { createGetCharacterDetailUseCase(get()) }
    viewModel { CharactersViewModel(get(), get()) }
}

private fun createFetchCharactersUseCase(repository: SwapiRepository)
        : FetchCharactersUseCase {
    return FetchCharactersUseCase(repository)
}

private fun createGetCharacterDetailUseCase(repository: SwapiRepository)
: GetCharacterDetailUseCase {
    return GetCharacterDetailUseCase(repository)
}