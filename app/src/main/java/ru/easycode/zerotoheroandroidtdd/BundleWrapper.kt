package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle

interface BundleWrapper {

    fun save(inputs: ArrayList<String>)

    fun restore(): ArrayList<String>

    class StringArrayList(private val bundle: Bundle?) : BundleWrapper {
        override fun save(inputs: ArrayList<String>) {
            bundle?.putStringArrayList(STRING_ARRAY_LIST_KEY, inputs)
        }

        override fun restore(): ArrayList<String> =
            bundle?.getStringArrayList(STRING_ARRAY_LIST_KEY) ?: ArrayList()

        companion object {
            private const val STRING_ARRAY_LIST_KEY = "stringArrayListKey"
        }
    }
}
