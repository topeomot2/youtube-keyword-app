package com.topeomot.youtubekeywordapp.data.db

import com.fasterxml.jackson.databind.ObjectMapper
import com.topeomot.youtubekeywordapp.data.db.database.YoutubeDb
import com.topeomot.youtubekeywordapp.data.db.entity.Cache
import com.topeomot.youtubekeywordapp.di.ApplicationScope
import com.topeomot.youtubekeywordapp.model.YoutubeResponse
import com.topeomot.youtubekeywordapp.util.YoutubeKeywordConstant
import io.reactivex.Maybe
import org.joda.time.DateTime
import javax.inject.Inject

@ApplicationScope
class YoutubeDbHelper @Inject constructor(
    val youtubeDb: YoutubeDb,
    val objectMapper: ObjectMapper
) : DbHelper {

    override fun isValid(cache: Cache): Boolean =
        cache.updateTime.isAfter(DateTime().minusMinutes(30))


    override fun getSearchResult(searchTerm: String, pageToken: String): Maybe<Cache> {
        return youtubeDb.cacheDao().getCacheByParameters2(searchTerm, YoutubeKeywordConstant.NUM_PAGE, pageToken!!)
    }

    override fun addSearchResult(searchTerm: String, pageToken: String, youtubeResponse: YoutubeResponse): Cache {
        val cache = Cache(searchTerm, YoutubeKeywordConstant.NUM_PAGE, pageToken,
            result = objectMapper.writeValueAsString(youtubeResponse))
        youtubeDb.cacheDao().insertCache(cache)
        return cache
    }

}