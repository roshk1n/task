package com.home.test.ui.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.home.test.R
import com.home.test.domain.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactsAdapter constructor(private val callback: Callback) :
    RecyclerView.Adapter<ContactViewHolder>() {

    interface Callback {
        fun onItemSelected(contact: Contact, position: Int)
    }

    private var contacts = mutableListOf<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder.create(parent, callback)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    fun setContacts(contactList: List<Contact>) {
        contacts.clear()
        contacts.addAll(contactList)
        notifyDataSetChanged()
    }
}

class ContactViewHolder constructor(view: View, callback: ContactsAdapter.Callback) :
    RecyclerView.ViewHolder(view) {

    private var contact: Contact? = null

    init {
        view.setOnClickListener {
            contact?.let {
                callback.onItemSelected(it, adapterPosition)
            }
        }
    }

    fun bind(contact: Contact) {
        this.contact = contact
        val fullName = contact.firstName + " " + contact.lastName
        itemView.name.text = fullName
    }

    companion object {
        fun create(parent: ViewGroup, callback: ContactsAdapter.Callback): ContactViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_contact, parent, false)
            return ContactViewHolder(view, callback)
        }
    }
}