package ru.easycode.zerotoheroandroidtdd.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.domain.Item
import ru.easycode.zerotoheroandroidtdd.ui.ItemUi
import ru.easycode.zerotoheroandroidtdd.ui.list.ListLiveDataWrapper

class MainViewModel(
    private val repository: Repository.Read,
    private val liveDataWrapper: ListLiveDataWrapper.All,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), LiveDataWrapper.Read<List<ItemUi>> {

    fun init() {
        viewModelScope.launch(dispatcherMain) {
            val inputs: List<Item>
            withContext(dispatcher) {
                inputs = repository.list()
            }
            liveDataWrapper.update(inputs.map { ItemUi(it.id, it.text) })
        }
    }

    override fun liveData(): LiveData<List<ItemUi>> = liveDataWrapper.liveData()
}
