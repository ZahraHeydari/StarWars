package com.android.domain.usecase

import com.android.domain.entity.CharacterResponseObject
import com.android.domain.repository.SwapiRepository
import com.android.domain.util.ResultOf

class FetchCharactersUseCase(
    private val repository: SwapiRepository
) : BaseUseCase<String?, ResultOf<CharacterResponseObject>>() {
    override suspend fun execute(params: String?): ResultOf<CharacterResponseObject> {
        return params?.let { repository.fetchItems(it) }
            ?: ResultOf.Failure(Throwable("Input parameter is invalid!"))
    }
}