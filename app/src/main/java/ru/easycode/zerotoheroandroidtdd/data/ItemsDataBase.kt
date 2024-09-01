package ru.easycode.zerotoheroandroidtdd.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ItemCache::class], version = 1, exportSchema = false)
abstract class ItemsDataBase : RoomDatabase(), ProvideDao {

    class Instance(context: Context, name: String) : ProvideDao {

        private val database by lazy {
            Room.databaseBuilder(context, ItemsDataBase::class.java, name).build()
        }

        override fun itemsDao(): ItemsDao = database.itemsDao()
    }
}

interface ProvideDao {
    fun itemsDao(): ItemsDao
}
