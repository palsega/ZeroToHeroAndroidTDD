package ru.easycode.zerotoheroandroidtdd.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.list.ListLiveDataWrapper

class AddViewModel(
    private val repository: Repository.Add,
    private val liveDataWrapper: ListLiveDataWrapper.Add,
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    fun add(value: String) {
        viewModelScope.launch(dispatcher) {
            repository.add(value)
            liveDataWrapper.add(value)
            comeback()
        }
    }

    fun comeback() {
        clear.clearViewModel(AddViewModel::class.java)
    }
}
