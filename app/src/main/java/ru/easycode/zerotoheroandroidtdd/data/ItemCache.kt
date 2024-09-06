package ru.easycode.zerotoheroandroidtdd.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("items_table")
data class ItemCache(
    @PrimaryKey
    @ColumnInfo("id") val id: Long,
    @ColumnInfo("text") val text: String
)
