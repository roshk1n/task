package com.home.test.ui.addcontact

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.home.test.R
import com.home.test.helpers.GuiHelper
import com.home.test.ui.extentions.text
import com.home.test.validator.exceptions.*
import kotlinx.android.synthetic.main.activity_add_contact.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddContactActivity : AppCompatActivity() {

    private val viewModel: AddContactViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        save.setOnClickListener {
            addContact()
        }

        viewModel.addAccountResponseLiveData.observe(this, Observer { result ->
            if (result) {
                Toast.makeText(this, getString(R.string.added), Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, getString(R.string.error_contact_add), Toast.LENGTH_LONG).show()
            }
        })

        viewModel.addAccountValidationLiveData.observe(this, Observer { exception ->
            handleErrorOnUI(exception)
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

    private fun addContact() {
        viewModel.addContact(
            firstName.text(),
            lastName.text(),
            email.text(),
            phone.text(),
            address.text()
        )
    }

    private fun handleErrorOnUI(exception: ValidatorException?) {
        exception?.let {
            val field = when (exception) {
                is FirstNameValidatorException -> {
                    firstName
                }
                is LastNameValidatorException -> {
                    lastName
                }
                is EmailValidatorException -> {
                    email
                }
                is PhoneValidatorException -> {
                    phone
                }
                is AddressValidatorException -> {
                    address
                }
                else -> null
            }
            field?.let {
                GuiHelper.setError(field, exception.error, true)
            }
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, AddContactActivity::class.java)
        }
    }
}