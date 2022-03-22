package com.hd.movie.widgets

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.view.inputmethod.InputMethodManager

import android.widget.EditText


fun setEditTextState(editText: EditText) {
    editText.isFocusable = true
    editText.isFocusableInTouchMode = true
    editText.requestFocus()
    Handler().postDelayed({
        val inputManager: InputMethodManager =
            editText.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.showSoftInput(editText, 0)
    }, 300)
}

//如果显示则隐藏，如果隐藏则显示
fun showOrHideSoftKeyboard(activity: Activity) {
    val imm: InputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (imm.isActive) {
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}