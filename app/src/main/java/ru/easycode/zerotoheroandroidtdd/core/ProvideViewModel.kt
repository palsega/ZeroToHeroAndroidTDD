package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.data.ItemsDao
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.ui.add.AddViewModel
import ru.easycode.zerotoheroandroidtdd.ui.delete.DeleteViewModel
import ru.easycode.zerotoheroandroidtdd.ui.list.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.ui.main.MainViewModel

interface ProvideViewModel {
    fun <T : ViewModel>viewModel(viewModelClass: Class<T>): T

    class Base(db: ItemsDao, private val clear: ClearViewModel) : ProvideViewModel {

        private val now = Now.Base()
        private val repository = Repository.Base(db, now)
        private val liveDataWrapper = ListLiveDataWrapper.Base()

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return when (viewModelClass) {
                MainViewModel::class.java -> MainViewModel(repository,liveDataWrapper)
                AddViewModel::class.java -> AddViewModel(repository, liveDataWrapper, clear)
                DeleteViewModel::class.java -> DeleteViewModel(liveDataWrapper, repository, clear)
                else -> throw IllegalArgumentException("Unknown ViewModel class")
            } as T
        }
    }
}