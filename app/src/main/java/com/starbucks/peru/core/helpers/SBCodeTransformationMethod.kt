package com.starbucks.peru.core.helpers

import android.text.method.PasswordTransformationMethod
import android.view.View

class SBCodeTransformationMethod(
    private val numberDigitsVisible: Int
): PasswordTransformationMethod() {

    override fun getTransformation(source: CharSequence?, view: View?): CharSequence {
        return if (source == null) {
            super.getTransformation(source, view)
        } else {
            PasswordCharSequence(source, numberDigitsVisible)
        }
    }

    private class PasswordCharSequence(
        source: CharSequence,
        numberDigitsVisible: Int
    ): CharSequence {
        private val _source = source
        private val _numberDigitsVisible = if (numberDigitsVisible < 0) {
            0
        } else {
            numberDigitsVisible
        }

        override val length: Int
            get() = _source.length

        override fun get(index: Int): Char {
            val digit = _source[index]
            val startIndex = if (_source.length < _numberDigitsVisible) {
                0
            } else {
                _source.length - _numberDigitsVisible
            }

            return when {
                digit == ' ' -> ' '
                index >= startIndex -> digit
                else -> '•'
            }
        }

        override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
            return _source.subSequence(startIndex, endIndex)
        }

    }
}