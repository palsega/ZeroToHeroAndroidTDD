package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper {

    interface Save {
        fun save(bundleWrapper: BundleWrapper.Save)
    }

    interface Update {
        fun update(value: UiState)
    }

    interface Mutable : Save, Update, ProvideLiveData<UiState>

    class Base(private val uiStateLiveData: MutableLiveData<UiState> = SingleLiveEvent()) :
        Mutable {

        override fun save(bundleWrapper: BundleWrapper.Save) {
            uiStateLiveData.value?.let { bundleWrapper.save(it) }
        }

        override fun update(value: UiState) {
            uiStateLiveData.value = value
        }

        override fun liveData(): LiveData<UiState> = uiStateLiveData
    }
}

interface ProvideLiveData<UiState> {
    fun liveData(): LiveData<UiState>
}
