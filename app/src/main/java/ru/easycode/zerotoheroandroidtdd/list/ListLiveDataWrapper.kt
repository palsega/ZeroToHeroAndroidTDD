package ru.easycode.zerotoheroandroidtdd.list

import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper

interface ListLiveDataWrapper {

    interface Read : LiveDataWrapper.Read<List<CharSequence>>

    interface Update : LiveDataWrapper.Update<List<CharSequence>>

    interface Mutable : Read, Update {
        fun save(bundleWrapper: BundleWrapper.Save)
    }

    interface Add {
        fun add(source: CharSequence)
    }

    interface All : Mutable, Add

    class Base : LiveDataWrapper.Abstract<List<CharSequence>>(), All {
        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let {
                bundleWrapper.save(ArrayList(it))
            }
        }

        override fun add(source: CharSequence) {
            val current = liveData.value?.let { ArrayList(it) } ?: ArrayList()
            current.add(source)
            update(current)
        }
    }
}