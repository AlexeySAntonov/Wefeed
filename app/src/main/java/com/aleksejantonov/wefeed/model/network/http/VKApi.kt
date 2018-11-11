package com.aleksejantonov.wefeed.model.network.http

import com.aleksejantonov.wefeed.model.network.entity.dislike.DislikeResponseContainer
import com.aleksejantonov.wefeed.model.network.entity.feed.FeedResponseContainer
import com.aleksejantonov.wefeed.model.network.entity.group.GroupResponseContainer
import com.aleksejantonov.wefeed.model.network.entity.like.LikeResponseContainer
import com.aleksejantonov.wefeed.model.network.entity.user.UserResponseContainer
import com.aleksejantonov.wefeed.model.network.http.config.ApiConstants.FIELDS
import com.aleksejantonov.wefeed.model.network.http.config.ApiConstants.GROUP_IDS
import com.aleksejantonov.wefeed.model.network.http.config.ApiConstants.ITEM_ID
import com.aleksejantonov.wefeed.model.network.http.config.ApiConstants.ITEM_TYPE
import com.aleksejantonov.wefeed.model.network.http.config.ApiConstants.OWNER_ID
import com.aleksejantonov.wefeed.model.network.http.config.ApiConstants.PHOTO_SMALL
import com.aleksejantonov.wefeed.model.network.http.config.ApiConstants.START_FROM
import com.aleksejantonov.wefeed.model.network.http.config.ApiConstants.TOKEN
import com.aleksejantonov.wefeed.model.network.http.config.ApiConstants.USER_ID
import com.aleksejantonov.wefeed.model.network.http.config.ApiConstants.VERSION
import com.aleksejantonov.wefeed.model.network.http.config.ApiConstants.VERSION_NUM
import com.aleksejantonov.wefeed.model.network.http.config.ApiMethods.DISLIKE
import com.aleksejantonov.wefeed.model.network.http.config.ApiMethods.GROUP_INFO
import com.aleksejantonov.wefeed.model.network.http.config.ApiMethods.LIKES_ADD
import com.aleksejantonov.wefeed.model.network.http.config.ApiMethods.NEWS_FEED
import com.aleksejantonov.wefeed.model.network.http.config.ApiMethods.USER_INFO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface VKApi {
  @GET(NEWS_FEED)
  fun newsFeed(
      @Query(START_FROM) chunk: String,
      @Query(TOKEN) token: String,
      @Query(VERSION) version: Double = VERSION_NUM
  ): Call<FeedResponseContainer>

  @GET(GROUP_INFO)
  fun groupsInfo(
      @Query(GROUP_IDS) ids: String,
      @Query(TOKEN) token: String,
      @Query(VERSION) version: Double = VERSION_NUM
  ): Call<GroupResponseContainer>

  @GET(USER_INFO)
  fun userInfo(
      @Query(USER_ID) id: Long,
      @Query(TOKEN) token: String,
      @Query(FIELDS) fields: String = PHOTO_SMALL,
      @Query(VERSION) version: Double = VERSION_NUM
  ): Call<UserResponseContainer>

  @GET(LIKES_ADD)
  fun addLike(
      @Query(ITEM_TYPE) type: String,
      @Query(OWNER_ID) ownerId: Long,
      @Query(ITEM_ID) itemId: Long,
      @Query(TOKEN) token: String,
      @Query(VERSION) version: Double = VERSION_NUM
  ): Call<LikeResponseContainer>

  @GET(DISLIKE)
  fun dislike(
      @Query(ITEM_TYPE) type: String,
      @Query(OWNER_ID) ownerId: Long,
      @Query(ITEM_ID) itemId: Long,
      @Query(TOKEN) token: String,
      @Query(VERSION) version: Double = VERSION_NUM
  ): Call<DislikeResponseContainer>
}