package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper {

    interface Read<T : Any> {
        fun liveData(): LiveData<T>
    }

    interface Update<T : Any> {
        fun update(value: T)
    }

    interface Mutable<T : Any> : Read<T>, Update<T>

    abstract class Abstract<T: Any>(protected val liveData: MutableLiveData<T> = MutableLiveData()): Read<T>,
        Update<T> {
        override fun liveData(): LiveData<T> = liveData

        override fun update(value: T) {
            liveData.value = value
        }
    }
}

