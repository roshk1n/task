package com.home.test.validator

import android.content.Context
import com.home.test.R
import com.home.test.validator.exceptions.*
import org.apache.commons.validator.routines.EmailValidator

class ValidationServiceImpl(private val context: Context) : ValidationService {

    private val emptyValidator: LengthValidator =
        LengthValidator(LengthValidator.LENGTH_AT_LEAST, 1)

    override fun validateEmail(email: String?): Boolean {
        if (!emptyValidator.validate(email)) {
            throw EmailValidatorException(context.getString(R.string.error_this_field_required))
        }

        if (!EmailValidator.getInstance().isValid(email)) {
            throw EmailValidatorException(context.getString(R.string.error_invalid_email))
        }
        return true
    }

    override fun validateFirstName(name: String?): Boolean {
        if (!emptyValidator.validate(name)) {
            throw FirstNameValidatorException(context.getString(R.string.error_this_field_required))
        }
        val lengthValidator = LengthValidator(LengthValidator.LENGTH_AT_LEAST, NAME_MIN_LENGTH)

        if (!lengthValidator.validate(name)) {
            throw FirstNameValidatorException(
                context.getString(
                    R.string.error_name_min_length,
                    NAME_MIN_LENGTH
                )
            )
        }
        return true
    }

    override fun validateLastName(lastName: String?): Boolean {
        if (!emptyValidator.validate(lastName)) {
            throw LastNameValidatorException(context.getString(R.string.error_this_field_required))
        }
        val lengthValidator = LengthValidator(LengthValidator.LENGTH_AT_LEAST, NAME_MIN_LENGTH)

        if (!lengthValidator.validate(lastName)) {
            throw LastNameValidatorException(
                context.getString(
                    R.string.error_name_min_length,
                    NAME_MIN_LENGTH
                )
            )
        }
        return true
    }

    override fun validateAddress(address: String?): Boolean {
        if (!emptyValidator.validate(address)) {
            throw AddressValidatorException(context.getString(R.string.error_this_field_required))
        }
        return true
    }

    override fun validatePhone(phone: String?): Boolean {
        if (!emptyValidator.validate(phone)) {
            throw PhoneValidatorException(context.getString(R.string.error_this_field_required))
        }
        return true
    }

    companion object {
        private const val NAME_MIN_LENGTH = 2
    }
}
