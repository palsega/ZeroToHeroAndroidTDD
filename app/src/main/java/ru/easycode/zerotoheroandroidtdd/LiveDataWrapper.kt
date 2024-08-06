package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper {

    fun save(bundleWrapper: BundleWrapper.Save)
    fun update(value: UiState)
    fun liveData(): LiveData<UiState>

    class Base(private val uiStateLiveData: MutableLiveData<UiState> = SingleLiveEvent<UiState>()) :
        LiveDataWrapper, ProvideLiveData {

        override fun save(bundleWrapper: BundleWrapper.Save) {
            uiStateLiveData.value?.let { bundleWrapper.save(it) }
        }

        override fun update(value: UiState) {
            uiStateLiveData.value = value
        }

        override fun liveData(): LiveData<UiState> = uiStateLiveData
    }
}
