package ru.easycode.zerotoheroandroidtdd.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.easycode.zerotoheroandroidtdd.domain.Item

@Entity("item")
data class ItemCache(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "text") val text: String
) {
    fun toItem(): Item = Item(id, text)
}
