package com.home.test.ui.addcontact

import com.home.test.domain.ContactRepository
import com.home.test.domain.model.Contact
import com.home.test.helpers.SingleLiveData
import com.home.test.ui.base.BaseViewModel
import com.home.test.validator.ContactValidator
import com.home.test.validator.exceptions.ValidatorException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AddContactViewModel(
    private val contactRepository: ContactRepository,
    private val contactValidator: ContactValidator
) : BaseViewModel() {

    val addAccountResponseLiveData = SingleLiveData<Boolean>()

    val addAccountValidationLiveData = SingleLiveData<ValidatorException>()

    fun addContact(
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        address: String
    ) {
        val contact = Contact(
            firstName = firstName,
            lastName = lastName,
            email = email,
            phone = phone,
            address = address
        )

        try {
            if (contactValidator.validate(contact)) {
                composite.add(contactRepository.addContact(contact)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { addAccountResponseLiveData.postValue(true) },
                        { addAccountResponseLiveData.postValue(false) }
                    ))
            }
        } catch (e: ValidatorException) {
            addAccountValidationLiveData.postValue(e)
        }
    }
}