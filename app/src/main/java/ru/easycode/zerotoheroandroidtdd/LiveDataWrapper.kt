package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper : ProvideLiveData {

    fun update(value: UiState)

    class Base(private val uiState: MutableLiveData<UiState> = MutableLiveData(UiState.Initial)) :
        LiveDataWrapper {

        override fun update(value: UiState) {
            uiState.value = value
        }

        override fun liveData(): LiveData<UiState> = uiState
    }
}

interface ProvideLiveData {
    fun liveData(): LiveData<UiState>
}