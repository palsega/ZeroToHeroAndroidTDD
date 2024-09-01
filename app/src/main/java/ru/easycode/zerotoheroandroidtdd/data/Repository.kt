package ru.easycode.zerotoheroandroidtdd.data

import ru.easycode.zerotoheroandroidtdd.core.Now

interface Repository {

    interface Read {
        fun list(): List<String>
    }

    interface Add {
        fun add(value: String)
    }

    interface Mutable : Read, Add

    class Base(private val dataSource: ItemsDao, private val now: Now) : Mutable {
        override fun list(): List<String> = dataSource.list().map { it.text }

        override fun add(value: String) {
            dataSource.add(ItemCache(now.nowMillis(), value))
        }
    }
}
