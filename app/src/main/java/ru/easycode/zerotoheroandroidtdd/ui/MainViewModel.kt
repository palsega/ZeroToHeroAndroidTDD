package ru.easycode.zerotoheroandroidtdd.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.list.ListLiveDataWrapper

class MainViewModel(
    private val repository: Repository.Read,
    private val liveDataWrapper: ListLiveDataWrapper.Mutable,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel(), LiveDataWrapper.Read<List<String>> {

    fun init() {
        viewModelScope.launch(dispatcher) {
            val current = repository.list()
            liveDataWrapper.update(current)
        }
    }

    override fun liveData(): LiveData<List<String>> = liveDataWrapper.liveData()
}
