package com.home.test.ui.contacts

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.home.test.R
import com.home.test.domain.model.Contact
import com.home.test.ui.addcontact.AddContactActivity
import com.home.test.ui.contactdetails.ContactDetailsActivity
import com.home.test.ui.extentions.setVisibility
import kotlinx.android.synthetic.main.activity_contacts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

open class ContactsActivity : AppCompatActivity() {

    private val viewModel: ContactsViewModel by viewModel()

    private lateinit var contactsAdapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        setEmptyStateVisibility(true)

        initContactsRecycler()

        viewModel.contactsLiveData.observe(this, Observer<List<Contact>> {
            setEmptyStateVisibility(it.isEmpty())
            contactsAdapter.setContacts(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_add_contact -> {
                startActivity(AddContactActivity.createIntent(this))
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setEmptyStateVisibility(isVisible: Boolean) {
        emptyGroup.setVisibility(isVisible)
        contactsRv.setVisibility(!isVisible)
    }

    private fun initContactsRecycler() {
        contactsAdapter = ContactsAdapter(object : ContactsAdapter.Callback {
            override fun onItemSelected(contact: Contact, position: Int) {
                startActivity(ContactDetailsActivity.createIntent(this@ContactsActivity, contact.id))
            }
        })
        contactsRv.adapter = contactsAdapter
    }
}