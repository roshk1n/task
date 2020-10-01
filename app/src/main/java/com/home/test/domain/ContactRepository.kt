package com.home.test.domain

import com.home.test.domain.model.Contact
import io.reactivex.Completable
import io.reactivex.Observable

interface ContactRepository {

    fun getAllContacts(): Observable<List<Contact>>

    fun addContact(contact: Contact): Completable

    fun getContact(contactId: Long): Observable<Contact>
}