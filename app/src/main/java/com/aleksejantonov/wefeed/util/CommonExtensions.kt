package com.aleksejantonov.wefeed.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

val Fragment.fragmentActivity: FragmentActivity?
  get() = activity
