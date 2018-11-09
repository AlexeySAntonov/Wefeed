package com.aleksejantonov.wefeed.model.preferences

import android.content.Context

class PreferencesManager(private val context: Context) {
  private companion object {
    const val PREFS_SCOPE = "main"
    const val VK_TOKEN = "vk_token"
  }

  private val sharedPreferences by lazy { context.getSharedPreferences(PREFS_SCOPE, Context.MODE_PRIVATE) }

  fun saveToken(token: String) = sharedPreferences.edit().putString(VK_TOKEN, token).apply()
  fun getToken() = sharedPreferences.getString(VK_TOKEN, "")
}