package ru.easycode.zerotoheroandroidtdd.core

import android.app.Application
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.data.ItemsDataBase

class App : Application(), ProvideViewModel {

    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()

        val clear: ClearViewModel = object : ClearViewModel {
            override fun clearViewModel(clasz: Class<out ViewModel>) {
                viewModelFactory.clearViewModel(clasz)
            }
        }

        val db = ItemsDataBase.Instance(this).dao()
        val provideViewModel = ProvideViewModel.Base(db, clear)
        viewModelFactory = ViewModelFactory.Base(provideViewModel)
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T =
        viewModelFactory.viewModel(viewModelClass)
}

