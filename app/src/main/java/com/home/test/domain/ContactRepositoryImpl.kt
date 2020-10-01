package com.home.test.domain

import com.home.test.domain.model.Contact
import com.home.test.domain.model.toContact
import com.home.test.domain.model.toContractEntry
import com.home.test.domain.persistent.DaoContactEntry
import io.reactivex.Completable
import io.reactivex.Observable

class ContactRepositoryImpl constructor(private val daoContactEntry: DaoContactEntry) :
    ContactRepository {

    override fun getAllContacts(): Observable<List<Contact>> {
        return daoContactEntry.getAll().map { list ->
            list.map { it.toContact() }
        }
    }

    override fun addContact(contact: Contact): Completable {
        return daoContactEntry.insert(contact.toContractEntry())
    }

    override fun getContact(contactId: Long): Observable<Contact> {
        return daoContactEntry.getById(contactId).map { it.toContact() }
    }
}