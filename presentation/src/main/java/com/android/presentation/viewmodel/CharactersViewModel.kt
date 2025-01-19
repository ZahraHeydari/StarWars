package com.android.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.entity.CharacterDetailObject
import com.android.domain.entity.CharacterResponseObject
import com.android.domain.usecase.FetchCharactersUseCase
import com.android.domain.usecase.GetCharacterDetailUseCase
import com.android.domain.util.ResultOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

    private val _characterResponseObject =
        MutableStateFlow<ResultOf<CharacterResponseObject>>(ResultOf.Success(CharacterResponseObject()))
    val characterResponseObject: StateFlow<ResultOf<CharacterResponseObject>> get() = _characterResponseObject

    private val _characterDetailObject =
        MutableStateFlow<ResultOf<CharacterDetailObject>>(ResultOf.Loading())
    val characterDetailObject: StateFlow<ResultOf<CharacterDetailObject>> get() = _characterDetailObject

    fun searchCharacters(query: String?) {
        if (query.isNullOrEmpty()) return
        viewModelScope.launch {
            kotlin.runCatching {
                fetchCharactersUseCase.invoke(query)
            }.onSuccess {
                it.collect { result ->
                    _characterResponseObject.value = result
                }
            }.onFailure {
                _characterResponseObject.value = ResultOf.Failure(it)
                it.printStackTrace()
            }
        }
    }

    fun getCharacterDetail(url: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                getCharacterDetailUseCase.invoke(url)
            }.onSuccess {
                it.collect { result ->
                    _characterDetailObject.value = result
                }
            }.onFailure {
                _characterDetailObject.value = ResultOf.Failure(it)
                it.printStackTrace()
            }
        }
    }
}