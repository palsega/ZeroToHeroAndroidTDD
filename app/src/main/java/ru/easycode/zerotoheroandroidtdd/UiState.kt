package ru.easycode.zerotoheroandroidtdd

import android.os.Parcelable
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.parcelize.Parcelize

interface UiState : Parcelable {

    fun apply(progressBar: ProgressBar, textView: TextView, button: Button)

    @Parcelize
    object Initial : UiState {
        override fun apply(progressBar: ProgressBar, textView: TextView, button: Button) {
            progressBar.visibility = View.INVISIBLE
            textView.visibility = View.INVISIBLE
            button.isEnabled = true
        }
    }

    @Parcelize
    object ShowProgress : UiState {
        override fun apply(progressBar: ProgressBar, textView: TextView, button: Button) {
            progressBar.visibility = View.VISIBLE
            textView.visibility = View.INVISIBLE
            button.isEnabled = false
        }
    }

    @Parcelize
    data class ShowData(private val text: String) : UiState {
        override fun apply(progressBar: ProgressBar, textView: TextView, button: Button) {
            progressBar.visibility = View.INVISIBLE
            textView.visibility = View.VISIBLE
            textView.text = text
            button.isEnabled = true
        }
    }
}