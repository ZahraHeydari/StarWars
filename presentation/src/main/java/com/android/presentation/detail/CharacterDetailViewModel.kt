package com.android.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.entity.CharacterDetailObject
import com.android.domain.usecase.GetCharacterDetailUseCase
import com.android.domain.util.ResultOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

    private val _characterDetailObject =
        MutableStateFlow<ResultOf<CharacterDetailObject>>(ResultOf.Loading())
    val characterDetailObject: StateFlow<ResultOf<CharacterDetailObject>> = _characterDetailObject

    fun getCharacterDetail(characterId: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                getCharacterDetailUseCase.invoke(characterId)
            }.onSuccess {
                _characterDetailObject.value = it
            }.onFailure {
                _characterDetailObject.value = ResultOf.Failure(it)
                it.printStackTrace()
            }
        }
    }
}