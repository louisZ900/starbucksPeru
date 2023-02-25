package com.starbucks.peru.core.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.starbucks.peru.core.helpers.SBCodeTransformationMethod

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //No op
        }

        override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //No op
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun EditText.addCardFormatWatcher(
    editText: EditText,
    lengthChainFormat: Int,
    numberDigitsVisible: Int,
    splitNumberDigits: Int
) {
    this.addTextChangedListener(object : TextWatcher {
        private val codeTransformation = SBCodeTransformationMethod(numberDigitsVisible)

        override fun afterTextChanged(editable: Editable?) {
            if (splitNumberDigits > 0) {
                val simpleText = editable.toString().replace(" ", "")
                val arrayText = simpleText.chunked(splitNumberDigits)
                val formattedText = arrayText.joinToString(" ")
                editText.removeTextChangedListener(this)
                editText.setText(formattedText)
                if (simpleText.length >= lengthChainFormat) {
                    editText.transformationMethod = codeTransformation
                } else {
                    editText.transformationMethod = null
                }
                editText.setSelection(editText.text.toString().length)
                editText.addTextChangedListener(this)
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //No op
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //No op
        }
    })
}

fun EditText.addCardFormatWatcherAndChange(
    editText: EditText,
    lengthChainFormat: Int,
    numberDigitsVisible: Int,
    splitNumberDigits: Int,
    enableAmexRule: Boolean = false,
    afterTextChanged: (String) -> Unit
) {
    this.addTextChangedListener(object : TextWatcher {
        private val codeTransformation = SBCodeTransformationMethod(numberDigitsVisible)

        override fun afterTextChanged(editable: Editable?) {
            if (splitNumberDigits > 0) {
                var simpleText = editable.toString().replace(" ", "")

                if (enableAmexRule && editable.toString().startsWith("3")) {
                    if (simpleText.length == 16) {
                        simpleText = simpleText.substring(0, 15)
                    }

                    if (simpleText.length == 15) {
                        editText.transformationMethod = codeTransformation
                    } else {
                        editText.transformationMethod = null
                    }
                } else {
                    if (simpleText.length >= lengthChainFormat) {
                        editText.transformationMethod = codeTransformation
                    } else {
                        editText.transformationMethod = null
                    }
                }

                val arrayText = simpleText.chunked(splitNumberDigits)
                val formattedText = arrayText.joinToString(" ")
                editText.removeTextChangedListener(this)
                editText.setText(formattedText)
                editText.setSelection(editText.text.toString().length)
                afterTextChanged.invoke(editable.toString())
                editText.addTextChangedListener(this)
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //No op
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //No op
        }
    })
}

fun EditText.removeCopyPaste() {
    this.customSelectionActionModeCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu): Boolean {
            menu.removeItem(android.R.id.copy)
            menu.removeItem(android.R.id.cut)
            return true
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode?) {}
    }
}