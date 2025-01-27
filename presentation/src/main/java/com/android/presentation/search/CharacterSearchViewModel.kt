package com.android.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.entity.CharacterResponseObject
import com.android.domain.usecase.FetchCharactersUseCase
import com.android.domain.util.ResultOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterSearchViewModel(
    private val fetchCharactersUseCase: FetchCharactersUseCase
) : ViewModel() {

    private val _characterResponseObject =
        MutableStateFlow<ResultOf<CharacterResponseObject>>(ResultOf.Nothing())
    val characterResponseObject: StateFlow<ResultOf<CharacterResponseObject>> =
        _characterResponseObject

    fun searchCharacters(query: String?) {
        if (query.isNullOrEmpty()) return
        viewModelScope.launch {
            kotlin.runCatching {
                fetchCharactersUseCase.invoke(query)
            }.onSuccess {
                _characterResponseObject.value = it
            }.onFailure {
                _characterResponseObject.value = ResultOf.Failure(it)
                it.printStackTrace()
            }
        }
    }
}