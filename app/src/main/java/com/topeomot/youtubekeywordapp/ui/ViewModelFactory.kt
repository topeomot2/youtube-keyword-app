package com.topeomot.youtubekeywordapp.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.SharedPreferences
import com.topeomot.youtubekeywordapp.YoutubeKeywordApplication
import com.topeomot.youtubekeywordapp.data.DataRepository
import com.topeomot.youtubekeywordapp.ui.main.MainViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(val application: YoutubeKeywordApplication,
                                           val dataRepository: DataRepository,
                                           val sharedPreferences: SharedPreferences) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dataRepository, sharedPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class") as Throwable
    }
}