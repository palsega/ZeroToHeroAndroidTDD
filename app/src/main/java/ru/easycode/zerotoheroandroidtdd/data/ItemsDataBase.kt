package ru.easycode.zerotoheroandroidtdd.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ItemCache::class], version = 1, exportSchema = false)
abstract class ItemsDataBase : RoomDatabase() {

    abstract fun itemsDao(): ItemsDao

    class Instance(context: Context) {
        private val database by lazy {
            Room
                .databaseBuilder(context, ItemsDataBase::class.java, "database")
                .build()
        }

        fun dao() = database.itemsDao()
    }
}