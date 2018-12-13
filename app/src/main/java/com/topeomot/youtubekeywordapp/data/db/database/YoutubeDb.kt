package com.topeomot.youtubekeywordapp.data.db.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.topeomot.youtubekeywordapp.data.db.converter.JodaDateTimeConverter
import com.topeomot.youtubekeywordapp.data.db.dao.CacheDao
import com.topeomot.youtubekeywordapp.data.db.entity.Cache

@Database(entities = [Cache::class], version = 1)
@TypeConverters(JodaDateTimeConverter::class)
abstract class YoutubeDb : RoomDatabase() {
    abstract fun cacheDao(): CacheDao
}