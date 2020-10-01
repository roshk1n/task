package com.home.test.di

import androidx.room.Room
import com.home.test.domain.ContactRepository
import com.home.test.domain.ContactRepositoryImpl
import com.home.test.domain.persistent.BookDatabase
import com.home.test.ui.addcontact.AddContactViewModel
import com.home.test.ui.contactdetails.ContactDetailsViewModel
import com.home.test.ui.contacts.ContactsViewModel
import com.home.test.validator.ContactValidator
import com.home.test.validator.ContactValidatorImpl
import com.home.test.validator.ValidationService
import com.home.test.validator.ValidationServiceImpl
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val appModule = module {

    single {
        Room.databaseBuilder(
            get(),
            BookDatabase::class.java,
            "book_database"
        ).build()
    }

    single { get<BookDatabase>().daoContactEntry() }

    single<ContactRepository> { ContactRepositoryImpl(get()) }

    single<ValidationService> { ValidationServiceImpl(get()) }

    single<ContactValidator> { ContactValidatorImpl(get()) }

    viewModel { ContactsViewModel(get()) }

    viewModel { AddContactViewModel(get(), get()) }

    viewModel { ContactDetailsViewModel(get()) }

}
