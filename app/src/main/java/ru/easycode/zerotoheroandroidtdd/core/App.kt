package ru.easycode.zerotoheroandroidtdd.core

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel {

    private val viewModelFactory = ViewModelFactory.Base(ProvideViewModel.Base())
    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>) =
        viewModelFactory.viewModel(viewModelClass)
}