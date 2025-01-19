package com.android.domain.usecase

import com.android.domain.entity.CharacterResponseObject
import com.android.domain.repository.SwapiRepository
import com.android.domain.util.ResultOf
import kotlinx.coroutines.flow.Flow
import java.security.InvalidParameterException

class FetchCharactersUseCase(
    private val repository: SwapiRepository
) : BaseUseCase<String?, Flow<ResultOf<CharacterResponseObject>>>() {
    override suspend fun execute(params: String?): Flow<ResultOf<CharacterResponseObject>> {
        return params?.let { repository.fetchItems(it) }
            ?: throw InvalidParameterException("Input parameter is invalid!")
    }
}