package ru.easycode.zerotoheroandroidtdd.ui.list

import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.ui.ItemUi

interface ListLiveDataWrapper {

    interface Add {
        fun add(value: ItemUi)
    }

    interface Delete {
        fun delete(item: ItemUi)
    }

    interface All : LiveDataWrapper.Mutable<List<ItemUi>>, Add, Delete

    class Base : LiveDataWrapper.Abstract<List<ItemUi>>(), All {
        override fun add(value: ItemUi) {
            val current = liveData.value?.let { ArrayList(it) } ?: ArrayList()
            current.add(value)
            update(current)
        }

        override fun delete(item: ItemUi) {
            val current = liveData.value?.let { ArrayList(it) } ?: ArrayList()
            current.remove(item)
            update(current)
        }
    }
}
