package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.data.Repository

class MainViewModelFactory(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(liveDataWrapper, repository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")

    }
}