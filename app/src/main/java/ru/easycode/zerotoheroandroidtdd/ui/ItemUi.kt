package ru.easycode.zerotoheroandroidtdd.ui

import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.ui.delete.DeleteItemUi

data class ItemUi(val id: Long, val text: String) {

    fun areItemsTheSame(other: ItemUi) = id == other.id

    fun delete(deleteItemUi: DeleteItemUi) = deleteItemUi.delete(id)

    fun show(textView: TextView) {
        textView.text = text
    }
}
