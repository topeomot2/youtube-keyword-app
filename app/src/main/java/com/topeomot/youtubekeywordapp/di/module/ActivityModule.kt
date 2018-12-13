package com.topeomot.youtubekeywordapp.di.module

import android.app.Activity
import android.content.Context
import com.topeomot.youtubekeywordapp.YoutubeKeywordApplication
import com.topeomot.youtubekeywordapp.data.DataRepository
import com.topeomot.youtubekeywordapp.di.ActivityScope
import com.topeomot.youtubekeywordapp.ui.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ActivityModule(val activity: Activity) {

    @Provides
    @ActivityScope
    fun provideActivityContext(): Context = activity


}