package com.aleksejantonov.wefeed.ui.auth

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aleksejantonov.wefeed.R
import com.aleksejantonov.wefeed.util.fragmentActivity
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk

class AuthFragment : Fragment() {
  companion object {
    fun newInstance() = AuthFragment()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_auth, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    fragmentActivity?.let { VKSdk.login(it, VKScope.FRIENDS, VKScope.WALL) }
  }
}