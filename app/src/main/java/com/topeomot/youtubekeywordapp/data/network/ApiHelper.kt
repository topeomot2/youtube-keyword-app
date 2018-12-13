package com.topeomot.youtubekeywordapp.data.network

import com.topeomot.youtubekeywordapp.model.YoutubeResponse
import io.reactivex.Flowable

interface ApiHelper {
    fun getSearchResult(searchTerm: String, pageToken: String?): Flowable<YoutubeResponse>
}