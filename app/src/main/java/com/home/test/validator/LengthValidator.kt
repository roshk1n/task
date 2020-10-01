package com.home.test.validator

import androidx.annotation.IntDef
import androidx.annotation.IntRange

class LengthValidator(
    /** Condition.  */
    @param:LengthCondition @field:LengthCondition
    private val condition: Int,
    /** Length.  */
    @param:IntRange(from = 0) private val length: Int
) {

    /** Length constant annotation.  */
    @IntDef(LENGTH_MORE_THAN, LENGTH_LESS_THAN, LENGTH_EQUAL, LENGTH_AT_LEAST)
    annotation class LengthCondition

    fun validate(text: String?): Boolean {
        if (text == null) return false

        val fieldLength = text.length
        return when (condition) {
            LENGTH_MORE_THAN -> fieldLength > this.length
            LENGTH_AT_LEAST -> fieldLength >= this.length
            LENGTH_EQUAL -> fieldLength == this.length
            LENGTH_LESS_THAN -> fieldLength < this.length
            LENGTH_NOT_MORE -> fieldLength <= this.length
            else -> false
        }
    }

    companion object {

        /** Length checking constants.  */
        const val LENGTH_MORE_THAN = -1
        const val LENGTH_LESS_THAN = -2
        const val LENGTH_EQUAL = -3
        const val LENGTH_AT_LEAST = -4
        const val LENGTH_NOT_MORE = -5
    }
}
