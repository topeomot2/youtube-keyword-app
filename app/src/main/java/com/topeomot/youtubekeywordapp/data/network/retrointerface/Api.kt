package com.topeomot.youtubekeywordapp.data.network.retrointerface

import com.topeomot.youtubekeywordapp.model.YoutubeResponse
import com.topeomot.youtubekeywordapp.util.YoutubeKeywordConstant
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET(YoutubeKeywordConstant.SEARCH_URL)
    fun getSearchResult(
        @Query("q") searchTerm: String,
        @Query("maxResults") numPage: Int,
        @Query("key") apiKey: String,
        @Query("part") part: String = "snippet",
        @Query("order") order: String = "relevance",
        @Query("pageToken") token: String? = null,
        @Query("type") type: String = "video,channel,playlist"
    ): Flowable<YoutubeResponse>
}