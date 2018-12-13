package com.topeomot.youtubekeywordapp.data

import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.topeomot.youtubekeywordapp.data.db.DbHelper
import com.topeomot.youtubekeywordapp.data.db.entity.Cache
import com.topeomot.youtubekeywordapp.data.network.ApiHelper
import com.topeomot.youtubekeywordapp.di.ApplicationScope
import com.topeomot.youtubekeywordapp.model.YoutubeResponse
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import org.joda.time.DateTime
import javax.inject.Inject
import javax.inject.Singleton

@ApplicationScope
class YoutubeRepository  @Inject constructor(val apiHelper: ApiHelper,
                                             val dbHelper: DbHelper,
                                             val objectMapper: ObjectMapper
) : DataRepository{

    override fun isValid(cache: Cache): Boolean =
        cache.updateTime.isAfter(DateTime().minusMinutes(30))


    override fun getSearchResult(searchTerm: String, pageToken: String): Flowable<YoutubeResponse> {
        val networkSource: Flowable<Cache> = apiHelper.getSearchResult(searchTerm, pageToken)
            .map { youtubeResponse: YoutubeResponse? ->  dbHelper.addSearchResult(searchTerm, pageToken, youtubeResponse!!
            )}
            .subscribeOn(Schedulers.io())

        val dbSource: Maybe<Cache> = dbHelper.getSearchResult(searchTerm, pageToken)
            .subscribeOn(Schedulers.computation())

        return Flowable
            .concat(dbSource.toFlowable(), networkSource)
            .filter{ t: Cache -> isValid(t) }
            .firstElement()
            .toFlowable()
            .map { t: Cache ->
                var youtubeResponse: YoutubeResponse = objectMapper.readValue(t.result)
                youtubeResponse
            }

    }
}