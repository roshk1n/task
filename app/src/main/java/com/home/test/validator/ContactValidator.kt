package com.home.test.validator

import com.home.test.domain.model.Contact
import com.home.test.validator.exceptions.ValidatorException

interface ContactValidator {
    @Throws(ValidatorException::class)
    fun validate(contract: Contact) : Boolean
}