package com.home.test.domain.model

import com.home.test.domain.persistent.model.ContactEntry

data class Contact(
    val id: Long = 0,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val phone: String?,
    val address: String?
)

fun ContactEntry.toContact(): Contact {
    return Contact(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        phone = this.phone,
        address = this.address
    )
}

fun Contact.toContractEntry(): ContactEntry {
    return ContactEntry(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        phone = this.phone,
        address = this.address
    )
}