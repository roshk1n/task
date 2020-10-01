package com.home.test.validator

import com.home.test.validator.exceptions.*

/** Validation service.  */
interface ValidationService {

    @Throws(EmailValidatorException::class)
    fun validateEmail(email: String?): Boolean

    @Throws(FirstNameValidatorException::class)
    fun validateFirstName(name: String?): Boolean

    @Throws(LastNameValidatorException::class)
    fun validateLastName(lastName: String?): Boolean

    @Throws(AddressValidatorException::class)
    fun validateAddress(address: String?): Boolean

    @Throws(PhoneValidatorException::class)
    fun validatePhone(phone: String?): Boolean

}
