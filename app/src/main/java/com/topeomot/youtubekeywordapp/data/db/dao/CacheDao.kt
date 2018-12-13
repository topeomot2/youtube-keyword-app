package com.topeomot.youtubekeywordapp.data.db.dao

import android.arch.persistence.room.*
import com.topeomot.youtubekeywordapp.data.db.entity.Cache
import io.reactivex.Flowable
import io.reactivex.Maybe


@Dao
interface CacheDao {

    @Query("SELECT * FROM cache WHERE searchTerm = :searchTerm AND size = :size AND token = :token")
    fun getCacheByParameters(
        searchTerm: String,
        size: Int,
        token: String
    ): Flowable<Cache>

    @Query("SELECT * FROM cache WHERE searchTerm = :searchTerm AND size = :size AND token = :token")
    fun getCacheByParameters2(
        searchTerm: String,
        size: Int,
        token: String
    ): Maybe<Cache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCache(cache: Cache)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCache(cache: Cache)

    @Delete
    fun deleteCache(cache: Cache)
}