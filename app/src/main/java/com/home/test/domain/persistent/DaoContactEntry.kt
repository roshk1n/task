package com.home.test.domain.persistent

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.home.test.domain.persistent.model.ContactEntry
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface DaoContactEntry {

    @Insert
    fun insert(contact: ContactEntry): Completable

    @Query("SELECT * FROM contracts_table WHERE id = :contactId")
    fun getById(contactId: Long): Observable<ContactEntry>

    @Query("SELECT * FROM contracts_table")
    fun getAll(): Observable<List<ContactEntry>>
}