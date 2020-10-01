package com.home.test.ui.contactdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.home.test.R
import com.home.test.domain.model.Contact
import kotlinx.android.synthetic.main.activity_contact_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactDetailsActivity : AppCompatActivity() {

    private val viewModel: ContactDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        readArgs()

        viewModel.contactLiveData.observe(this, Observer {
            supportActionBar?.title = "${it.firstName} ${it.lastName}"
            updateContact(it)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateContact(contact: Contact) {
        firstName.setText(contact.firstName)
        lastName.setText(contact.lastName)
        email.setText(contact.email)
        phone.setText(contact.phone)
        address.setText(contact.address)
    }

    private fun readArgs() {
        intent?.let {
            val contactId = it.getLongExtra(CONTACT_ID_KEY, 0);
            viewModel.setContactId(contactId)
        }
    }

    companion object {

        private const val CONTACT_ID_KEY = "contact_id_key"

        fun createIntent(context: Context, contactId: Long): Intent {
            return Intent(context, ContactDetailsActivity::class.java).apply {
                putExtra(CONTACT_ID_KEY, contactId)
            }
        }
    }
}