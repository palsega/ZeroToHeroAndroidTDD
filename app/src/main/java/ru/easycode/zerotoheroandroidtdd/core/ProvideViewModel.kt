package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.list.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.list.ListViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T

    class Base : ProvideViewModel {

        private val navigation = Navigation.Base()

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return when (viewModelClass) {
                MainViewModel::class.java -> MainViewModel(navigation)
                ListViewModel::class.java -> ListViewModel(ListLiveDataWrapper.Base(), navigation)
                else -> throw IllegalArgumentException("Unknown ViewModel class")
            } as T
        }
    }
}
