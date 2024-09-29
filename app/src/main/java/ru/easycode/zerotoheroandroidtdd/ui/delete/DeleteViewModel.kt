package ru.easycode.zerotoheroandroidtdd.ui.delete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

class DeleteViewModel(
    private val deleteLiveDataWrapper: ListLiveDataWrapper.All,
    private val repository: Repository.Delete,
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : VMWithComeBack() {

    private val _liveData: MutableLiveData<String> = MutableLiveData()
    val liveData: LiveData<String> get() = _liveData

    fun init(itemId: Long) {
        viewModelScope.launch(dispatcherMain) {
            val text: String
            withContext(dispatcher) {
                text = repository.item(itemId).text
            }
            _liveData.value = text
        }
    }

    fun delete(itemId: Long) {
        viewModelScope.launch(dispatcherMain) {
            withContext(dispatcher) {
                repository.delete(itemId)
            }

            deleteLiveDataWrapper.delete(ItemUi(itemId, liveData.value ?: ""))
            comeback()
        }
    }

    override fun comeback() {
        clear.clearViewModel(DeleteViewModel::class.java)
    }
}
