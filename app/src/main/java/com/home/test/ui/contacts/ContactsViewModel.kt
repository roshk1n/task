package com.home.test.ui.contacts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.home.test.ui.base.BaseViewModel
import com.home.test.domain.ContactRepository
import com.home.test.domain.model.Contact
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ContactsViewModel(private val contactRepository: ContactRepository) : BaseViewModel() {

    val contactsLiveData = MutableLiveData<List<Contact>>()

    init {
        loadContacts()
    }

    private fun loadContacts() {
        composite.add(contactRepository.getAllContacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    contactsLiveData.postValue(result)
                },
                { error ->
                    Log.e("load contacts error", "${error.message}")
                }
            ))
    }
}


