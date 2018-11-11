package com.aleksejantonov.wefeed.util

import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.aleksejantonov.wefeed.ui.main.MainActivity

val Fragment.fragmentActivity: FragmentActivity?
  get() = activity

val Fragment.mainActivity: MainActivity?
  get() = activity as MainActivity

fun MainActivity.toast(text: String) {
  Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun MainActivity.toast(@StringRes textRes: Int) {
  Toast.makeText(this, getString(textRes), Toast.LENGTH_SHORT).show()
}

var View.visible: Boolean
  get() = visibility == View.VISIBLE
  set(value) {
    visibility = if (value) View.VISIBLE else View.GONE
  }

fun <T> List<T>?.notNullNotEmpty() = this != null && this.isNotEmpty()

fun <T> List<T>?.nullOrEmpty() = this == null || this.isEmpty()

fun TextView.isEllipsized(): Boolean {
  val layout = this.layout
  layout?.let {
    val lines = it.lineCount
    if (lines > 0) {
      if (it.getEllipsisCount(lines - 1) > 0) return true
    }
  }
  return false
}