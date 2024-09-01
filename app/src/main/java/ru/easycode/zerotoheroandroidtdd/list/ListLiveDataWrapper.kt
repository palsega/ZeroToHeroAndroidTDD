package ru.easycode.zerotoheroandroidtdd.list

import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper

interface ListLiveDataWrapper {

    interface Add {
        fun add(value: String)
    }

    interface Mutable : LiveDataWrapper.Mutable<List<String>>
    class Base : LiveDataWrapper.Abstract<List<String>>(), Mutable, Add {
        override fun add(value: String) {
            val current = liveData.value?.let { ArrayList(it) } ?: ArrayList()
            current.add(value)
            update(current)
        }
    }
}
