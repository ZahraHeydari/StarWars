package com.android.starwarsapp.di

import com.android.domain.repository.SwapiRepository
import com.android.domain.usecase.FetchCharactersUseCase
import com.android.domain.usecase.GetCharacterDetailUseCase
import com.android.presentation.detail.CharacterDetailViewModel
import com.android.presentation.search.CharacterSearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { createFetchCharactersUseCase(get()) }
    single { createGetCharacterDetailUseCase(get()) }
    viewModelOf(::CharacterSearchViewModel)
    viewModelOf(::CharacterDetailViewModel)
}

private fun createFetchCharactersUseCase(repository: SwapiRepository)
        : FetchCharactersUseCase {
    return FetchCharactersUseCase(repository)
}

private fun createGetCharacterDetailUseCase(repository: SwapiRepository)
: GetCharacterDetailUseCase {
    return GetCharacterDetailUseCase(repository)
}