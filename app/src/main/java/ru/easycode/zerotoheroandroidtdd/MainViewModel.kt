package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class MainViewModel(private val listLiveDataWrapper: ListLiveDataWrapper) : ViewModel() {

    fun liveData() = listLiveDataWrapper.liveData()

    fun add(text: String) {
        listLiveDataWrapper.add(text)
    }

    fun save(bundle: BundleWrapper.Save) {
        listLiveDataWrapper.save(bundle)
    }

    fun restore(bundle: BundleWrapper.Restore) {
        listLiveDataWrapper.update(bundle.restore())
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val listLiveDataWrapper = ListLiveDataWrapper.Base()
                MainViewModel(listLiveDataWrapper)
            }
        }
    }
}
