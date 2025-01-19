package com.android.domain.usecase

import com.android.domain.entity.CharacterDetailObject
import com.android.domain.repository.SwapiRepository
import com.android.domain.util.ResultOf
import kotlinx.coroutines.flow.Flow
import java.security.InvalidParameterException

class GetCharacterDetailUseCase(
    private val repository: SwapiRepository
) : BaseUseCase<String?, Flow<ResultOf<CharacterDetailObject>>>() {
    override suspend fun execute(params: String?): Flow<ResultOf<CharacterDetailObject>> {
        return params?.let { repository.getCharacterDetail(it) }
            ?: throw InvalidParameterException("Input parameter is invalid!")
    }
}