package com.aleksejantonov.wefeed.sl.component

import android.content.Context
import com.aleksejantonov.wefeed.model.network.http.config.ApiConstants
import com.aleksejantonov.wefeed.model.preferences.PreferencesManager
import com.aleksejantonov.wefeed.util.navigation.AppRouter
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.vk.sdk.api.VKApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppComponent(private val context: Context) {
  val appRouter by lazy { AppRouter() }
  val preferencesManager by lazy { PreferencesManager(context) }
  private val client by lazy {
    OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .build()
  }
  val api by lazy {
    Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(ApiConstants.BASE_URL)
        .build()
        .create(VKApi::class.java)
  }
}