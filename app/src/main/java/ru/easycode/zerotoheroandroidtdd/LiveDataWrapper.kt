package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper : ProvideLiveData<UiState> {

    fun save(bundleWrapper: BundleWrapper.Save)

    fun update(value: UiState)

    override fun liveData(): LiveData<UiState>

    class Base(private val uiStateLiveData: MutableLiveData<UiState> = SingleLiveEvent()) :
        LiveDataWrapper {
        override fun save(bundleWrapper: BundleWrapper.Save) {
            uiStateLiveData.value?.let { bundleWrapper.save(it) }
        }

        override fun update(value: UiState) {
            uiStateLiveData.value = value
        }

        override fun liveData(): LiveData<UiState> = uiStateLiveData
    }
}