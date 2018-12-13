package com.topeomot.youtubekeywordapp.ui.main

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.topeomot.youtubekeywordapp.YoutubeKeywordApplication
import com.topeomot.youtubekeywordapp.data.DataRepository
import com.topeomot.youtubekeywordapp.model.YoutubeResponse

import com.topeomot.youtubekeywordapp.model.exception.NoSubjectMatterException
import com.topeomot.youtubekeywordapp.util.YoutubeKeywordConstant
import io.reactivex.Flowable

class MainViewModel(val dataRepository: DataRepository,
                    val sharedPreferences: SharedPreferences
) : ViewModel() {



    fun getSubjectMatter(): String {
        val subjectMatter = sharedPreferences.getString(YoutubeKeywordConstant.SUBJECT_MATTER
            , "")
        if(subjectMatter!!.isNullOrBlank()) {
            throw NoSubjectMatterException()
        } else {
            return subjectMatter
        }
    }

    fun setSubjectMatter(subjectMatter: String): Boolean {
        val editor = sharedPreferences.edit()
        editor.putString(YoutubeKeywordConstant.SUBJECT_MATTER,
            subjectMatter)
        return editor.commit()

    }

    fun search(searchTerm: String, pageToken: String = ""): Flowable<YoutubeResponse> =
        dataRepository.getSearchResult(searchTerm, pageToken)

}
