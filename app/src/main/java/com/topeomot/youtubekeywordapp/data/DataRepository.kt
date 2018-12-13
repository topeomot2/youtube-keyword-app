package com.topeomot.youtubekeywordapp.data

import com.topeomot.youtubekeywordapp.data.db.entity.Cache
import com.topeomot.youtubekeywordapp.model.YoutubeResponse
import io.reactivex.Flowable

interface DataRepository {
    fun isValid(cache: Cache): Boolean
    fun getSearchResult(searchTerm: String, pageToken: String = ""): Flowable<YoutubeResponse>
}