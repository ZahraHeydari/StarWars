package com.android.domain.usecase

import com.android.domain.entity.CharacterDetailObject
import com.android.domain.repository.SwapiRepository
import com.android.domain.util.ResultOf

class GetCharacterDetailUseCase(
    private val repository: SwapiRepository
) : BaseUseCase<String?, ResultOf<CharacterDetailObject>>() {
    override suspend fun execute(params: String?): ResultOf<CharacterDetailObject> {
        return params?.let { repository.getCharacterDetail(it) }
            ?: ResultOf.Failure(Throwable("Input parameter is invalid!"))
    }
}