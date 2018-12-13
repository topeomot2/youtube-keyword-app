package com.topeomot.youtubekeywordapp.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.topeomot.youtubekeywordapp.YoutubeKeywordApplication
import com.topeomot.youtubekeywordapp.data.db.database.YoutubeDb
import com.topeomot.youtubekeywordapp.di.ApplicationScope
import com.topeomot.youtubekeywordapp.util.YoutubeKeywordConstant
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(val application: YoutubeKeywordApplication) {

    @Provides
    @ApplicationScope
    fun provideApplicationContext(): Context = application.applicationContext


    @Provides
    @ApplicationScope
    fun provideYoutubeKeywordApplication(): YoutubeKeywordApplication = application

    @Provides
    @ApplicationScope
    fun provideSharedPreferences(): SharedPreferences = application
        .getSharedPreferences(YoutubeKeywordConstant.SHARED_PREFERENCE_NAME, MODE_PRIVATE)

    @Provides
    @ApplicationScope
    fun provideRoomDatabase(): YoutubeDb {
        return Room.databaseBuilder(
            application,
            YoutubeDb::class.java,
            "YouTubedfghjfffr89")
            .build()
    }
}