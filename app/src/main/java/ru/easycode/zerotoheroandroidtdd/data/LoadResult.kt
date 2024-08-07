package ru.easycode.zerotoheroandroidtdd.data

import ru.easycode.zerotoheroandroidtdd.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.UiState

interface LoadResult {
    fun show(updateLiveData: LiveDataWrapper.Update)

    data class Success(val data: SimpleResponse) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(UiState.ShowData(data.text))
        }
    }

    data class Error(val noConnection: Boolean) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            val errorMessage = if (noConnection) NO_INTERNET_MSG else SOMETHING_WRONG_MSG
            updateLiveData.update(UiState.ShowData(errorMessage))
        }

        companion object {
            private const val NO_INTERNET_MSG = "No internet connection"
            private const val SOMETHING_WRONG_MSG = "Something went wrong"
        }
    }
}
