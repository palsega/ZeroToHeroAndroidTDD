package ru.easycode.zerotoheroandroidtdd.data

import ru.easycode.zerotoheroandroidtdd.core.Now
import ru.easycode.zerotoheroandroidtdd.domain.Item

interface Repository {
    interface Read {
        fun list(): List<Item>
    }

    interface Add {
        fun add(value: String): Long
    }

    interface Delete {
        fun delete(id: Long)
        fun item(id: Long): Item
    }

    interface All : Read, Add, Delete
    class Base(private val dataSource: ItemsDao, private val now: Now) : All {
        override fun list(): List<Item> = dataSource.list().map { it.toItem() }


        override fun add(value: String): Long {
            val id = now.nowMillis()
            dataSource.add(ItemCache(id, value))
            return id
        }

        override fun delete(id: Long) {
            dataSource.delete(id)
        }

        override fun item(id: Long): Item = dataSource.item(id).toItem()
    }
}
