package com.android.presentation.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.entity.CharacterDetailObject
import com.android.domain.usecase.GetCharacterDetailUseCase
import com.android.domain.util.ResultOf
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

    var characterDetailObject = mutableStateOf<ResultOf<CharacterDetailObject>>(ResultOf.Loading())
        private set

    fun getCharacterDetail(characterId: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                getCharacterDetailUseCase.invoke(characterId)
            }.onSuccess {
                characterDetailObject.value = it
            }.onFailure {
                characterDetailObject.value = ResultOf.Failure(it)
                it.printStackTrace()
            }
        }
    }
}