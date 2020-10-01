package com.home.test.ui.contactdetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.home.test.ui.base.BaseViewModel
import com.home.test.domain.ContactRepository
import com.home.test.domain.model.Contact
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ContactDetailsViewModel(private val contactRepository: ContactRepository) : BaseViewModel() {

    val contactLiveData = MutableLiveData<Contact>()

    fun setContactId(contactId: Long) {
        composite.add(contactRepository.getContact(contactId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { contactLiveData.postValue(it) },
                { Log.d("getContactById", "${it.message}") }
            ))
    }
}