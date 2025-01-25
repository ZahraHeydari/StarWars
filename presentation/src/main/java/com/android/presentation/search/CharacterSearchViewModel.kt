package com.android.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.entity.CharacterResponseObject
import com.android.domain.usecase.FetchCharactersUseCase
import com.android.domain.util.ResultOf
import kotlinx.coroutines.launch

class CharacterSearchViewModel(
    private val fetchCharactersUseCase: FetchCharactersUseCase
) : ViewModel() {

    var characterResponseObject:
            ResultOf<CharacterResponseObject> = ResultOf.Success(CharacterResponseObject())
        private set

    fun searchCharacters(query: String?) {
        if (query.isNullOrEmpty()) return
        viewModelScope.launch {
            kotlin.runCatching {
                fetchCharactersUseCase.invoke(query)
            }.onSuccess {
                characterResponseObject = it
            }.onFailure {
                characterResponseObject = ResultOf.Failure(it)
                it.printStackTrace()
            }
        }
    }
}