package ru.easycode.zerotoheroandroidtdd

import android.os.Parcelable
import android.widget.Button
import android.widget.TextView
import kotlinx.parcelize.Parcelize

interface UiState : Parcelable {

    fun apply(
        countTextView: TextView,
        decrementButton: Button,
        incrementButton: Button
    )

    abstract class Abstract(private val text: String) : UiState {
        override fun apply(
            countTextView: TextView,
            decrementButton: Button,
            incrementButton: Button
        ) {
            countTextView.text = text
            decrementButton.isEnabled = true
            incrementButton.isEnabled = true
        }
    }

    @Parcelize
    data class Base(val text: String) : Abstract(text)

    @Parcelize
    data class Min(val text: String) : Abstract(text) {
        override fun apply(
            countTextView: TextView,
            decrementButton: Button,
            incrementButton: Button
        ) {
            super.apply(countTextView, incrementButton, decrementButton)
            decrementButton.isEnabled = false
            incrementButton.isEnabled = true
        }
    }

    @Parcelize
    data class Max(val text: String) : Abstract(text) {
        override fun apply(
            countTextView: TextView,
            decrementButton: Button,
            incrementButton: Button
        ) {
            super.apply(countTextView, incrementButton, decrementButton)
            decrementButton.isEnabled = true
            incrementButton.isEnabled = false
        }
    }
}