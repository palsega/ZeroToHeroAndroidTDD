package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper {
    fun update(value: UiState)

    fun liveData(): LiveData<UiState>

    class Base : LiveDataWrapper {

        private val uiState = MutableLiveData<UiState>(UiState.Initial)

        override fun update(value: UiState) {
            uiState.value = value
        }

        override fun liveData(): LiveData<UiState> = uiState
    }
}
