package com.kforce.myurbandictionay.home.ui.dictionary

import com.kforce.myurbandictionay.home.repository.model.api.model.DefinationModel

sealed class DictionaryState {
    abstract val data: List<DefinationModel>
    abstract val loadedAllItems: Boolean
}


data class DefaultState(override val data: List<DefinationModel>, override val loadedAllItems: Boolean) :
    DictionaryState()

data class LoadingState(override val data: List<DefinationModel>, override val loadedAllItems: Boolean) :
    DictionaryState()

data class ErrorState(val errorMessage: String, override val data: List<DefinationModel>, override val loadedAllItems: Boolean) :
    DictionaryState()

