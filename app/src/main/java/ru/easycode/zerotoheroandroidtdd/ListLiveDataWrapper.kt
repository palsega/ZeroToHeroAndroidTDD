package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map

interface ListLiveDataWrapper {

    fun liveData(): LiveData<List<CharSequence>>

    fun add(new: CharSequence)

    fun save(bundle: BundleWrapper.Save)

    fun update(list: List<CharSequence>)

    class Base(private val listLiveData: MutableLiveData<ArrayList<CharSequence>> = SingleLiveEvent()) :
        ListLiveDataWrapper {

        override fun liveData(): LiveData<List<CharSequence>> = listLiveData.map { it.toList() }

        override fun add(new: CharSequence) {
            val current = listLiveData.value ?: ArrayList()
            current.add(new)
            update(current)
        }

        override fun save(bundle: BundleWrapper.Save) {
            listLiveData.value?.let { bundle.save(ArrayList(it)) }
        }

        override fun update(list: List<CharSequence>) {
            listLiveData.value = ArrayList(list)
        }
    }
}