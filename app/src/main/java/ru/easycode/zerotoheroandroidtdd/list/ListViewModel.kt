package ru.easycode.zerotoheroandroidtdd.list

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.Navigation
import ru.easycode.zerotoheroandroidtdd.create.CreateScreen

class ListViewModel(
    private val liveDataWrapper: ListLiveDataWrapper.Mutable,
    private val navigation: Navigation.Update
) : ViewModel() {

    fun create() {
        navigation.update(CreateScreen)
    }

    fun save(bundleWrapper: BundleWrapper.Save) {
        liveDataWrapper.save(bundleWrapper)
    }

    fun restore(bundleWrapper: BundleWrapper.Restore) {
        liveDataWrapper.update(bundleWrapper.restore())
    }
}
