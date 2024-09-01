package ru.easycode.zerotoheroandroidtdd.core

import android.app.Application
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.data.ItemsDataBase

class App : Application(), ProvideViewModel {

    private lateinit var factory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()

        val clearViewModel = object : ClearViewModel {
            override fun clearViewModel(clasz: Class<out ViewModel>) {
                factory.clearViewModel(clasz)
            }
        }
        val dao = ItemsDataBase.Instance(this, "database").itemsDao()
        val provideViewModel = ProvideViewModel.Base(dao, clearViewModel)
        factory = ViewModelFactory.Base(provideViewModel)
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T =
        factory.viewModel(viewModelClass)
}