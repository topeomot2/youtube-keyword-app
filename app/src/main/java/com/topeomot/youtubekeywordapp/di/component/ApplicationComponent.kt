package com.topeomot.youtubekeywordapp.di.component

import android.content.SharedPreferences
import com.topeomot.youtubekeywordapp.YoutubeKeywordApplication
import com.topeomot.youtubekeywordapp.data.DataRepository
import com.topeomot.youtubekeywordapp.di.ApplicationScope
import com.topeomot.youtubekeywordapp.di.module.NetworkModule
import dagger.Component

@ApplicationScope
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun provideDataRepository(): DataRepository
    fun provideYoutubeKeywordApplication(): YoutubeKeywordApplication
    fun provideSharedPreferences(): SharedPreferences
    fun inject(youtubeKeywordApplication: YoutubeKeywordApplication)
}