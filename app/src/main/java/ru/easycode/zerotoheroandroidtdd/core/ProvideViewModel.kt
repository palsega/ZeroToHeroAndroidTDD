package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.create.CreateViewModel
import ru.easycode.zerotoheroandroidtdd.list.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.list.ListViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T

    class Base(private val clearViewModel: ClearViewModel) : ProvideViewModel {

        private val navigation = Navigation.Base()
        private val liveDataWrapper = ListLiveDataWrapper.Base()

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return when (viewModelClass) {
                MainViewModel::class.java -> MainViewModel(navigation)
                ListViewModel::class.java -> ListViewModel(liveDataWrapper, navigation)
                CreateViewModel::class.java -> CreateViewModel(
                    liveDataWrapper,
                    navigation,
                    clearViewModel
                )
                else -> throw IllegalArgumentException("Unknown ViewModel class")
            } as T
        }
    }
}
