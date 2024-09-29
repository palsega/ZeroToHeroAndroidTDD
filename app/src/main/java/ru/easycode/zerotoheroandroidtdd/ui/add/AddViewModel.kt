package ru.easycode.zerotoheroandroidtdd.ui.add

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.core.VMWithComeBack
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.ui.ItemUi
import ru.easycode.zerotoheroandroidtdd.ui.list.ListLiveDataWrapper

class AddViewModel(
    private val repository: Repository.Add,
    private val liveDataWrapper: ListLiveDataWrapper.Add,
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : VMWithComeBack() {

    fun add(value: String) {
        viewModelScope.launch(dispatcherMain) {
            val id: Long
            withContext(dispatcher) {
                id = repository.add(value)
            }
            liveDataWrapper.add(ItemUi(id, value))
            comeback()
        }
    }

    override fun comeback() {
        clear.clearViewModel(AddViewModel::class.java)
    }
}
