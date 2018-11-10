package com.aleksejantonov.wefeed.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View

val Fragment.fragmentActivity: FragmentActivity?
  get() = activity

var View.visible: Boolean
  get() = visibility == View.VISIBLE
  set(value) {
    visibility = if (value) View.VISIBLE else View.GONE
  }
