package com.home.test.validator

import com.home.test.domain.model.Contact
import com.home.test.validator.exceptions.ValidatorException

class ContactValidatorImpl(private val validationService: ValidationService) : ContactValidator {

    @Throws(ValidatorException::class)
    override fun validate(contract: Contact): Boolean {
        validationService.validateFirstName(contract.firstName)
        validationService.validateLastName(contract.lastName)
        validationService.validateEmail(contract.email)
        validationService.validateAddress(contract.address)
        validationService.validatePhone(contract.phone)
        return true
    }

}