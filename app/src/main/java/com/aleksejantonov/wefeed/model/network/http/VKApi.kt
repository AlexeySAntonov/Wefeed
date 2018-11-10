package com.aleksejantonov.wefeed.model.network.http

import com.aleksejantonov.wefeed.model.network.entity.feed.ResponseContainer
import com.aleksejantonov.wefeed.model.network.http.config.ApiConstants.TOKEN
import com.aleksejantonov.wefeed.model.network.http.config.ApiConstants.VERSION
import com.aleksejantonov.wefeed.model.network.http.config.ApiMethods.NEWS_FEED
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface VKApi {
  @GET(NEWS_FEED)
  fun newsFeed(
      @Query(VERSION) version: Double = 5.52,
      @Query(TOKEN) token: String
  ): Call<ResponseContainer>
}