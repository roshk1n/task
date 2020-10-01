package com.home.test.domain.persistent

import androidx.room.Database
import androidx.room.RoomDatabase
import com.home.test.domain.persistent.model.ContactEntry

@Database(entities = [ContactEntry::class], version = 1)
abstract class BookDatabase : RoomDatabase() {
    abstract fun daoContactEntry(): DaoContactEntry
}