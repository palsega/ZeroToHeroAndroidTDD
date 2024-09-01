package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel

interface ViewModelFactory : ProvideViewModel, ClearViewModel {

    class Base(private val provideViewModel: ProvideViewModel) : ViewModelFactory {

        private val cache: MutableMap<Class<out ViewModel>, ViewModel> = mutableMapOf()

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return cache.getOrPut(viewModelClass) {
                provideViewModel.viewModel(viewModelClass)
            } as T
        }

        override fun clearViewModel(clasz: Class<out ViewModel>) {
            cache.remove(clasz)
        }
    }
}