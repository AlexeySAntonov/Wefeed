package com.aleksejantonov.wefeed.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aleksejantonov.wefeed.R
import com.aleksejantonov.wefeed.sl.SL
import com.aleksejantonov.wefeed.ui.Screens.AUTH_SCREEN
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError

class MainActivity : AppCompatActivity() {

  private val router by lazy { SL.componentManager().appComponent().appRouter }
  private val preferencesManager by lazy { SL.componentManager().appComponent().preferencesManager }

  override fun onCreate(savedInstanceState: Bundle?) {
    router.createNavigator(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    if (preferencesManager.getToken().isNotBlank()) router.openMain()
    else router.replace(AUTH_SCREEN)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    val vkCallback = object : VKCallback<VKAccessToken> {
      override fun onResult(token: VKAccessToken) {
        preferencesManager.saveToken(token.accessToken)
        router.openMain()
      }

      override fun onError(error: VKError) {
        Log.e("VKAccessError", error.errorMessage)
      }
    }

    if (VKSdk.onActivityResult(requestCode, resultCode, data, vkCallback).not()) {
      super.onActivityResult(requestCode, resultCode, data)
    }
  }
}
