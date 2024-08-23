package ru.easycode.zerotoheroandroidtdd.core

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel {

    private val clearViewModel: ClearViewModel = object : ClearViewModel {
        override fun clear(viewModelClass: Class<out ViewModel>) {
            factory.clear(viewModelClass)
        }
    }

    private val factory = ViewModelFactory.Base(ProvideViewModel.Base(clearViewModel))
    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>) =
        factory.viewModel(viewModelClass)
}